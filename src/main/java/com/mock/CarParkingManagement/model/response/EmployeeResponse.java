package com.mock.CarParkingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeResponse {
    private Long employeeId;
    private String username;
    private String department;
    private String employeeAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate employeeBirthday;
    private String employeeEmail;
    private String employeeName;
    private String employeePhone;
    private String sex;

}
