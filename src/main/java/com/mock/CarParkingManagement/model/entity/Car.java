package com.mock.CarParkingManagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @Column(length = 50)
    private String licensePlate;

    @Column(length = 11)
    private String carColor;

    @Column(length = 11)
    private String carType;

    @Column(length = 50)
    private String company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "park_id")
    private ParkingLot parkingLot;

//    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true)
//    private Ticket ticket;
}
