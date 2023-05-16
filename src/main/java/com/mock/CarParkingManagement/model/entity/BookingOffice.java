package com.mock.CarParkingManagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeId;

    private LocalDate endContractDeadline;

    @Column(length = 50)
    private String officeName;

    @Column(length = 15)
    private String officePhone;

    @Column(length = 50)
    private String officePlace;

    private Long officePrice;

    private LocalDate startContractDeadline;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "trip_id")
    private Trip trip;
}
