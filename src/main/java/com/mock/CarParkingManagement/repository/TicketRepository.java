package com.mock.CarParkingManagement.repository;

import com.mock.CarParkingManagement.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketId(Long id);
    boolean existsByTicketId(Long id);
}
