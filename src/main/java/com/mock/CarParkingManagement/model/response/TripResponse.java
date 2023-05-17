package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    @JsonIgnoreProperties({"trip"})
    private List<BookingOfficeResponse> bookingOffices;
    @JsonIgnoreProperties("trip")
    private List<TicketResponse> tickets;
}
