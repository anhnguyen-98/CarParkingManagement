package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.exception.EntityNotFoundException;
import com.mock.CarParkingManagement.model.entity.Ticket;
import com.mock.CarParkingManagement.model.entity.Trip;
import com.mock.CarParkingManagement.model.dto.TicketDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.TicketResponse;
import com.mock.CarParkingManagement.model.response.TripResponse;
import com.mock.CarParkingManagement.repository.TicketRepository;
import com.mock.CarParkingManagement.repository.TripRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomPage<TicketResponse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Ticket> pagedResult = ticketRepository.findAll(pageable);
        List<Ticket> tickets = new ArrayList<>();
        if (pagedResult.hasContent()) {
            tickets = pagedResult.getContent();
        }
        List<TicketResponse> ticketResponseList = tickets
                .stream().map(ticket -> modelMapper.map(ticket, TicketResponse.class))
                .collect(Collectors.toList());
        CustomPage<TicketResponse> ticketResponsePage = new CustomPage<>();
        ticketResponsePage.setContent(ticketResponseList);
        ticketResponsePage.setCurrentPage(pagedResult.getNumber());
        ticketResponsePage.setTotalItems(pagedResult.getTotalElements());
        ticketResponsePage.setTotalPages(pagedResult.getTotalPages());
        return ticketResponsePage;
    }

    @Override
    public TicketResponse findById(Long id) {
        return modelMapper.map(ticketRepository.findByTicketId(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Ticket with id = " + id + " not existed");
        }), TicketResponse.class);
    }

    @Override
    public TicketResponse addTicket(TicketDTO ticketDTO) {
        Long tripId = ticketDTO.getTripId();
        Trip trip = tripRepository.findByTripId(tripId).orElseThrow(() -> {
            throw new EntityNotFoundException("Trip with id = " + tripId + " not existed");
        });
        Ticket ticketToAdd = modelMapper.map(ticketDTO, Ticket.class);
        ticketToAdd.setTicketId(null);
        ticketToAdd.setTrip(trip);
        return modelMapper.map(ticketRepository.save(ticketToAdd), TicketResponse.class);
    }

    @Override
    public TicketResponse updateTicket(Long id, TicketDTO ticketDTO) {
        if (!ticketRepository.existsByTicketId(id)) {
            throw new EntityNotFoundException("Ticket with id = " + id + " not existed");
        }
        Long tripId = ticketDTO.getTripId();
        Trip trip = tripRepository.findByTripId(tripId).orElseThrow(() -> {
            throw new EntityNotFoundException("Trip with id = " + tripId + " not existed");
        });
        Ticket ticketToAdd = modelMapper.map(ticketDTO, Ticket.class);
        ticketToAdd.setTicketId(id);
        ticketToAdd.setTrip(trip);
        return modelMapper.map(ticketRepository.save(ticketToAdd), TicketResponse.class);
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsByTicketId(id)) {
            throw new EntityNotFoundException("Ticket with id = " + id + " not existed");
        }
        ticketRepository.deleteById(id);
    }
}
