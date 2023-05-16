package com.mock.CarParkingManagement.model.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class ParkingLotDTO {
    private String parkArea;
    private String parkName;
    private String parkPlace;
    private Long parkPrice;
    private String parkStatus;
}
