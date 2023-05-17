package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.exception.EntityNotFoundException;
import com.mock.CarParkingManagement.exception.LicensePlateExistedException;
import com.mock.CarParkingManagement.model.dto.CarDTO;
import com.mock.CarParkingManagement.model.entity.Car;
import com.mock.CarParkingManagement.model.entity.ParkingLot;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.CarResponse;
import com.mock.CarParkingManagement.repository.CarRepository;
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
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ParkingLotRepository parkingLotRepository;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, ParkingLotRepository parkingLotRepository) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public CustomPage<CarResponse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo < 1) {
            throw new IllegalArgumentException("Page index must not be less than one");
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));
        Page<Car> pagedResult = carRepository.findAll(pageable);
        List<Car> cars = new ArrayList<>();
        if (pagedResult.hasContent()) {
            cars = pagedResult.getContent();
        }
        List<CarResponse> carResponseList = cars
                .stream().map(car -> modelMapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
        CustomPage<CarResponse> carResponsePage = new CustomPage<>();
        carResponsePage.setContent(carResponseList);
        carResponsePage.setCurrentPage(pagedResult.getNumber() + 1);
        carResponsePage.setTotalItems(pagedResult.getTotalElements());
        carResponsePage.setTotalPages(pagedResult.getTotalPages());
        return carResponsePage;
    }

    @Override
    public CarResponse findByLicensePlate(String licensePlate) {
        return modelMapper.map(carRepository.findByLicensePlate(licensePlate).orElseThrow(() -> {
            throw new EntityNotFoundException("Car with license plate = " + licensePlate + " not existed");
        }), CarResponse.class);
    }

    @Override
    public CarResponse addCar(CarDTO carDTO) {
        if (carRepository.existsByLicensePlate(carDTO.getLicensePlate())) {
            throw new LicensePlateExistedException("Car with license plate = " + carDTO.getLicensePlate() + " existed");
        }
        Long parkId = carDTO.getParkId();
        ParkingLot parkingLot = parkingLotRepository.findById(parkId).orElseThrow(() -> {
            throw new EntityNotFoundException("Parking Lot with id = " + parkId + " not existed");
        });
        Car carToAdd = modelMapper.map(carDTO, Car.class);
        carToAdd.setParkingLot(parkingLot);
        return modelMapper.map(carRepository.save(carToAdd), CarResponse.class);
    }

    @Override
    public CarResponse updateCar(String licensePlate, CarDTO carDTO) {
        if (!carRepository.existsByLicensePlate(licensePlate)) {
            throw new EntityNotFoundException("Car with license plate = " + licensePlate + " not existed");
        }
        Long parkId = carDTO.getParkId();
        ParkingLot parkingLot = parkingLotRepository.findById(parkId).orElseThrow(() -> {
            throw new EntityNotFoundException("Parking Lot with id = " + parkId + " not existed");
        });
        Car carToUpdate = modelMapper.map(carDTO, Car.class);
        carToUpdate.setLicensePlate(licensePlate);
        carToUpdate.setParkingLot(parkingLot);
        return modelMapper.map(carRepository.save(carToUpdate), CarResponse.class);
    }

    @Override
    @Transactional
    public void deleteCar(String licensePlate) {
        if (!carRepository.existsByLicensePlate(licensePlate)) {
            throw new EntityNotFoundException("Car with license plate = " + licensePlate + " not existed");
        }
        carRepository.deleteByLicensePlate(licensePlate);
    }
}
