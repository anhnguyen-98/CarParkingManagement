package com.mock.CarParkingManagement.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkId;

    private Long parkArea;

    @Column(length = 50)
    private String parkName;

    @Column(length = 11)
    private String parkPlace;

    private Long parkPrice;

    @Column(length = 50)
    private String parkStatus;

    @OneToMany(mappedBy = "parkingLot", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Car> cars;
}
