package com.mock.CarParkingManagement.controller;

import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.MessageResponse;
import com.mock.CarParkingManagement.model.dto.TripDTO;
import com.mock.CarParkingManagement.model.response.TripResponse;
import com.mock.CarParkingManagement.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping("/all")
    public ResponseEntity<CustomPage<TripResponse>> findAllTrips(
            @RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "5", name = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "tripId", name = "sortBy") String sortBy
    ) {
        return ResponseEntity.ok(tripService.findAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<TripResponse> createCar(@RequestBody TripDTO tripDTO) {
        return ResponseEntity.ok(tripService.addTrip(tripDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> findCarByLicensePlate(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripResponse> updateCar(@PathVariable Long id, @RequestBody TripDTO tripDTO) {
        return ResponseEntity.ok(tripService.updateTrip(id, tripDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteCar(@PathVariable Long id) {
        try {
            tripService.deleteTrip(id);
            return ResponseEntity.ok(new MessageResponse("successfully delete", LocalDateTime.now()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), LocalDateTime.now()));
        }
    }
}
