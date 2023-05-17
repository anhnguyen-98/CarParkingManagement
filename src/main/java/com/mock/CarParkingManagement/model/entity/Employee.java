package com.mock.CarParkingManagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "username", length = 50)
    private String username;

    @Column(length = 10)
    private String department;

    @Column(length = 50)
    private String employeeAddress;

    private LocalDate employeeBirthday;

    @Column(length = 50)
    private String employeeEmail;

    @Column(length = 50)
    private String employeeName;

    @Column(length = 15)
    private String employeePhone;

    @Column(length = 20)
    @JsonIgnore
    private String password;

    @Column(length = 10)
    private String sex;
}
