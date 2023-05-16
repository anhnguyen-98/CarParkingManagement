package com.mock.CarParkingManagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;

    @Column(length = 10)
    private int bookedTicketNumber;

    @Column(length = 11)
    private String carType;

    private LocalDate departureDate;

    private LocalTime departureTime;

    @Column(length = 50)
    private String destination;

    @Column(length = 50)
    private String driver;

    @Column(length = 11)
    private int maximumOnlineTicketNumber;

    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "trip", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BookingOffice> bookingOffices;
}
