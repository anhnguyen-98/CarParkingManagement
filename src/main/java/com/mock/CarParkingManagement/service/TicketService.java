package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.TicketDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.TicketResponse;

import java.util.List;

public interface TicketService {
    CustomPage<TicketResponse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    TicketResponse findById(Long id);

    TicketResponse addTicket(TicketDTO ticketDTO);

    TicketResponse updateTicket(Long id, TicketDTO ticketDTO);

    void deleteTicket(Long id);
}
