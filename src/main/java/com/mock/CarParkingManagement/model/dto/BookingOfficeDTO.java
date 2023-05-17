package com.mock.CarParkingManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
public class BookingOfficeDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endContractDeadline;
    private String officeName;
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "invalid phone number")
    private String officePhone;
    private String officePlace;
    private Long officePrice;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startContractDeadline;
    private Long tripId;
}
