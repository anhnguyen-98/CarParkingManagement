package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingLotResponse {
    private Long parkId;
    private Long parkArea;
    private String parkName;
    private String parkPlace;
    private Long parkPrice;
    private String parkStatus;
}
