package com.mock.CarParkingManagement.repository;

import com.mock.CarParkingManagement.model.entity.BookingOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingOfficeRepository extends JpaRepository<BookingOffice, Long> {
    Optional<BookingOffice> findByOfficeId(Long id);
    boolean existsByOfficeId(Long id);
}
