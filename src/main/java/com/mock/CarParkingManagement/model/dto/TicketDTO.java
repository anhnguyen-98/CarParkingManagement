package com.mock.CarParkingManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;


@Getter
public class TicketDTO {
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime bookingTime;
    private String customerName;
    private Long tripId;
}
