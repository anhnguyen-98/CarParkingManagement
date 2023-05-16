package com.mock.CarParkingManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mock.CarParkingManagement.util.password.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class EmployeeDTO {
    private String username;
    private String department;
    private String employeeAddress;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate employeeBirthday;
    private String employeeEmail;
    private String employeeName;
    private String employeePhone;
    @ValidPassword
    private String password;
    private String sex;
}
