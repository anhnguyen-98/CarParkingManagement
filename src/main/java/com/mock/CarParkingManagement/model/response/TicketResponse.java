package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
public class TicketResponse {
    private Long ticketId;
    private LocalTime bookingTime;
    private String customerName;
    @JsonIgnoreProperties({"bookingOffices", "tickets"})
    private TripResponse trip;
}
