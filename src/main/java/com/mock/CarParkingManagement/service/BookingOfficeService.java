package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.BookingOfficeDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.BookingOfficeResponse;

import java.util.List;

public interface BookingOfficeService {
    CustomPage<BookingOfficeResponse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    BookingOfficeResponse findById(Long id);

    BookingOfficeResponse addBookingOffice(BookingOfficeDTO bookingOfficeDTO);

    BookingOfficeResponse updateBookingOffice(Long id, BookingOfficeDTO bookingOfficeDTO);

    void deleteBookingOffice(Long id);
}
