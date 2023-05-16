package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.CarDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.CarResponse;

import java.util.List;

public interface CarService {
    CustomPage<CarResponse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    CarResponse findByLicensePlate(String licensePlate);

    CarResponse addCar(CarDTO carDTO);

    CarResponse updateCar(String licensePlate, CarDTO carDTO);

    void deleteCar(String licensePlate);
}
