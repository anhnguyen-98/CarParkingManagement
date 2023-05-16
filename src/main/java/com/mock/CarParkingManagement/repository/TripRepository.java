package com.mock.CarParkingManagement.repository;

import com.mock.CarParkingManagement.model.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Optional<Trip> findByTripId(Long id);
    boolean existsByTripId(Long id);
}
