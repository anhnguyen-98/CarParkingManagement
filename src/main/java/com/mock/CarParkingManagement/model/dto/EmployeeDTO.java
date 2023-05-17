package com.mock.CarParkingManagement.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mock.CarParkingManagement.util.password.ValidPassword;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
public class EmployeeDTO {
    @Size(min = 6, max = 15)
    private String username;
    private String department;
    private String employeeAddress;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate employeeBirthday;
    @Email
    private String employeeEmail;
    private String employeeName;
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "invalid phone number")
    private String employeePhone;
    @ValidPassword
    private String password;
    @Pattern(regexp = "[MF]", message = "sex must be either M or F")
    private String sex;
}
