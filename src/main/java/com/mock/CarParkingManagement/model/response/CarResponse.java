package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponse {
    private String licensePlate;
    private String carColor;
    private String carType;
    private String company;
    @JsonIgnoreProperties({"cars", "parkPrice", "parkStatus"})
    private ParkingLotResponse parkingLot;
}
