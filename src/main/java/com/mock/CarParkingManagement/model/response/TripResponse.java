package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TripResponse {
    private Long tripId;
    private int bookedTicketNumber;
    private String carType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate departureDate;
    private LocalTime departureTime;
    private String destination;
    private String driver;
    private int maximumOnlineTicketNumber;
}
