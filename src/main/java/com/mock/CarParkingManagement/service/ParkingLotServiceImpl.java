package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.exception.EntityNotFoundException;
import com.mock.CarParkingManagement.model.dto.ParkingLotDTO;
import com.mock.CarParkingManagement.model.entity.ParkingLot;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.ParkingLotResponse;
import com.mock.CarParkingManagement.repository.ParkingLotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;
    private final ModelMapper modelMapper;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository, ModelMapper modelMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomPage<ParkingLotResponse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo < 1) {
            throw new IllegalArgumentException("Page index must not be less than one");
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
        Page<ParkingLot> pagedResult = parkingLotRepository.findAll(pageable);
        List<ParkingLot> parkingLots = new ArrayList<>();
        if (pagedResult.hasContent()) {
            parkingLots = pagedResult.getContent();
        }
        List<ParkingLotResponse> parkingLotResponseList = parkingLots
                .stream().map(parkingLot -> modelMapper.map(parkingLot, ParkingLotResponse.class))
                .collect(Collectors.toList());
        CustomPage<ParkingLotResponse> parkingLotResponsePage = new CustomPage<>();
        parkingLotResponsePage.setContent(parkingLotResponseList);
        parkingLotResponsePage.setCurrentPage(pagedResult.getNumber() + 1);
        parkingLotResponsePage.setTotalItems(pagedResult.getTotalElements());
        parkingLotResponsePage.setTotalPages(pagedResult.getTotalPages());
        return parkingLotResponsePage;
    }

    @Override
    public ParkingLotResponse findById(Long id) {
        return modelMapper.map(parkingLotRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Parking Lot with id = " + id + " not existed");
        }), ParkingLotResponse.class);
    }

    @Override
    public ParkingLotResponse addParkingLot(ParkingLotDTO parkingLotDTO) {
        ParkingLot parkingLot = modelMapper.map(parkingLotDTO, ParkingLot.class);
        return modelMapper.map(parkingLotRepository.save(parkingLot), ParkingLotResponse.class);
    }

    @Override
    public ParkingLotResponse updateParkingLot(Long id, ParkingLotDTO parkingLotDTO) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Parking Lot with id = " + id + " not existed");
        });
        ParkingLot parkingLotToUpdate = modelMapper.map(parkingLotDTO, ParkingLot.class);
        parkingLotToUpdate.setParkId(parkingLot.getParkId());
        return modelMapper.map(parkingLotRepository.save(parkingLotToUpdate), ParkingLotResponse.class);
    }

    @Override
    @Transactional
    public void deleteParkingLot(Long id) {
        parkingLotRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Parking Lot with id = " + id + " not existed");
        });
        parkingLotRepository.deleteById(id);
    }
}
