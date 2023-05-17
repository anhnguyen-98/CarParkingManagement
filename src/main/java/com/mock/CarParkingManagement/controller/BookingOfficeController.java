package com.mock.CarParkingManagement.controller;

import com.mock.CarParkingManagement.model.dto.BookingOfficeDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.BookingOfficeResponse;
import com.mock.CarParkingManagement.model.response.MessageResponse;
import com.mock.CarParkingManagement.service.BookingOfficeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bookingOffice")
public class BookingOfficeController {
    private final BookingOfficeService bookingOfficeService;

    public BookingOfficeController(BookingOfficeService bookingOfficeService) {
        this.bookingOfficeService = bookingOfficeService;
    }

    @GetMapping("/all")
    public ResponseEntity<CustomPage<BookingOfficeResponse>> findAllBookingOffices(
            @RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "5", name = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "officeId", name = "sortBy") String sortBy) {
        return ResponseEntity.ok(bookingOfficeService.findAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<BookingOfficeResponse> createBookingOffice(@RequestBody BookingOfficeDTO bookingOfficeDTO) {
        return ResponseEntity.ok(bookingOfficeService.addBookingOffice(bookingOfficeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingOfficeResponse> findBookingOfficeById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingOfficeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingOfficeResponse> updateBookingOffice(@PathVariable Long id, @RequestBody BookingOfficeDTO bookingOfficeDTO) {
        return ResponseEntity.ok(bookingOfficeService.updateBookingOffice(id, bookingOfficeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteBookingOffice(@PathVariable Long id) {
        try {
            bookingOfficeService.deleteBookingOffice(id);
            return ResponseEntity.ok(new MessageResponse("successfully delete", LocalDateTime.now()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), LocalDateTime.now()));
        }
    }
}
