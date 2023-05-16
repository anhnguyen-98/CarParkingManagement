package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.exception.EntityNotFoundException;
import com.mock.CarParkingManagement.model.entity.BookingOffice;
import com.mock.CarParkingManagement.model.entity.Trip;
import com.mock.CarParkingManagement.model.dto.BookingOfficeDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.BookingOfficeResponse;
import com.mock.CarParkingManagement.repository.BookingOfficeRepository;
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
public class BookingOfficeServiceImpl implements BookingOfficeService {
    @Autowired
    private BookingOfficeRepository bookingOfficeRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomPage<BookingOfficeResponse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<BookingOffice> pagedResult = bookingOfficeRepository.findAll(pageable);
        List<BookingOffice> bookingOffices = new ArrayList<>();
        if (pagedResult.hasContent()) {
            bookingOffices = pagedResult.getContent();
        }
        List<BookingOfficeResponse> bookingOfficeResponseList = bookingOffices
                .stream().map(bookingOffice -> modelMapper.map(bookingOffice, BookingOfficeResponse.class))
                .collect(Collectors.toList());
        CustomPage<BookingOfficeResponse> bookingOfficeResponsePage = new CustomPage<>();
        bookingOfficeResponsePage.setContent(bookingOfficeResponseList);
        bookingOfficeResponsePage.setCurrentPage(pagedResult.getNumber());
        bookingOfficeResponsePage.setTotalItems(pagedResult.getTotalElements());
        bookingOfficeResponsePage.setTotalPages(pagedResult.getTotalPages());
        return bookingOfficeResponsePage;
    }

    @Override
    public BookingOfficeResponse findById(Long id) {
        return modelMapper.map(bookingOfficeRepository.findByOfficeId(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Booking Office with id = " + id + " not existed");
        }), BookingOfficeResponse.class);
    }

    @Override
    public BookingOfficeResponse addBookingOffice(BookingOfficeDTO bookingOfficeDTO) {
        Long tripId = bookingOfficeDTO.getTripId();
        Trip trip = tripRepository.findByTripId(tripId).orElseThrow(() -> {
            throw new EntityNotFoundException("Trip with id = " + tripId + " not existed");
        });
        BookingOffice bookingOfficeToAdd = modelMapper.map(bookingOfficeDTO, BookingOffice.class);
        bookingOfficeToAdd.setOfficeId(null);
        bookingOfficeToAdd.setTrip(trip);
        return modelMapper.map(bookingOfficeRepository.save(bookingOfficeToAdd), BookingOfficeResponse.class);
    }

    @Override
    public BookingOfficeResponse updateBookingOffice(Long id, BookingOfficeDTO bookingOfficeDTO) {
        Long tripId = bookingOfficeDTO.getTripId();
        Trip trip = tripRepository.findByTripId(tripId).orElseThrow(() -> {
            throw new EntityNotFoundException("Trip with id = " + tripId + " not existed");
        });
        BookingOffice bookingOfficeToUpdate = modelMapper.map(bookingOfficeDTO, BookingOffice.class);
        bookingOfficeToUpdate.setOfficeId(id);
        bookingOfficeToUpdate.setTrip(trip);
        return modelMapper.map(bookingOfficeRepository.save(bookingOfficeToUpdate), BookingOfficeResponse.class);
    }

    @Override
    @Transactional
    public void deleteBookingOffice(Long id) {
        if (!bookingOfficeRepository.existsByOfficeId(id)) {
            throw new EntityNotFoundException("Trip with id = " + id + " not existed");
        }
        bookingOfficeRepository.deleteById(id);
    }
}
