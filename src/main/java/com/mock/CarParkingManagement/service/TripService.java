package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.TripDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.TripResponse;

import java.util.List;

public interface TripService {
    CustomPage<TripResponse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    TripResponse findById(Long id);

    TripResponse addTrip(TripDTO tripDTO);

    TripResponse updateTrip(Long tripId, TripDTO tripDTO);

    void deleteTrip(Long id);
}
