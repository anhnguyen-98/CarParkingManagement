package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingLotResponse {
    private Long parkId;
    private Long parkArea;
    private String parkName;
    private String parkPlace;
    private Long parkPrice;
    private String parkStatus;
    @JsonIgnoreProperties("parkingLot")
    private List<CarResponse> cars;
}
