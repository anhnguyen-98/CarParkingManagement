package com.mock.CarParkingManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalTime;


@Getter
public class TicketDTO {
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;
    private String customerName;
    private Long tripId;
}
