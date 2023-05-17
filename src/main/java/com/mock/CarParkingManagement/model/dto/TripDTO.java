package com.mock.CarParkingManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class TripDTO {
    private int bookedTicketNumber;
    private String carType;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate departureDate;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime departureTime;
    private String destination;
    private String driver;
    private int maximumOnlineTicketNumber;
}
