package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.exception.EntityNotFoundException;
import com.mock.CarParkingManagement.model.dto.TripDTO;
import com.mock.CarParkingManagement.model.entity.Trip;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.TripResponse;
import com.mock.CarParkingManagement.repository.TripRepository;
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
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;

    public TripServiceImpl(TripRepository tripRepository, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomPage<TripResponse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo < 1) {
            throw new IllegalArgumentException("Page index must not be less than one");
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
        Page<Trip> pagedResult = tripRepository.findAll(pageable);
        List<Trip> trips = new ArrayList<>();
        if (pagedResult.hasContent()) {
            trips = pagedResult.getContent();
        }
        List<TripResponse> tripResponseList = trips
                .stream().map(trip -> modelMapper.map(trip, TripResponse.class))
                .collect(Collectors.toList());
        CustomPage<TripResponse> tripResponsePage = new CustomPage<>();
        tripResponsePage.setContent(tripResponseList);
        tripResponsePage.setCurrentPage(pagedResult.getNumber() + 1);
        tripResponsePage.setTotalItems(pagedResult.getTotalElements());
        tripResponsePage.setTotalPages(pagedResult.getTotalPages());
        return tripResponsePage;
    }

    @Override
    public TripResponse findById(Long id) {
        return modelMapper.map(tripRepository.findByTripId(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Trip with id = " + id + " not existed");
        }), TripResponse.class);
    }

    @Override
    public TripResponse addTrip(TripDTO tripDTO) {
        Trip tripToAdd = modelMapper.map(tripDTO, Trip.class);
        return modelMapper.map(tripRepository.save(tripToAdd), TripResponse.class);
    }

    @Override
    public TripResponse updateTrip(Long tripId, TripDTO tripDTO) {
        if (!tripRepository.existsByTripId(tripId)) {
            throw new EntityNotFoundException("Trip with id = " + tripId + " not existed");
        }
        Trip tripToUpdate = modelMapper.map(tripDTO, Trip.class);
        tripToUpdate.setTripId(tripId);
        return modelMapper.map(tripRepository.save(tripToUpdate), TripResponse.class);
    }

    @Override
    @Transactional
    public void deleteTrip(Long id) {
        if (!tripRepository.existsByTripId(id)) {
            throw new EntityNotFoundException("Trip with id = " + id + " not existed");
        }
        tripRepository.deleteById(id);
    }
}
