package com.mock.CarParkingManagement.repository;

import com.mock.CarParkingManagement.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    void deleteByLicensePlate(String licensePlate);
    Optional<Car> findByLicensePlate(String licensePlate);
    boolean existsByLicensePlate(String licensePlate);
}
