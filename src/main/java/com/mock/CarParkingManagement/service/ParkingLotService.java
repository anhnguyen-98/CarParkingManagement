package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.ParkingLotDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.ParkingLotResponse;

import java.util.List;

public interface ParkingLotService {
    CustomPage<ParkingLotResponse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    ParkingLotResponse findById(Long id);

    ParkingLotResponse addParkingLot(ParkingLotDTO parkingLotDTO);

    ParkingLotResponse updateParkingLot(Long id, ParkingLotDTO parkingLotDTO);

    void deleteParkingLot(Long id);
}
