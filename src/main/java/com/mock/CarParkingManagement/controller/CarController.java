package com.mock.CarParkingManagement.controller;

import com.mock.CarParkingManagement.model.dto.CarDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.MessageResponse;
import com.mock.CarParkingManagement.model.response.CarResponse;
import com.mock.CarParkingManagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/all")
    public ResponseEntity<CustomPage<CarResponse>> findAllCars(
            @RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "5", name = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "licensePlate", name = "sortBy") String sortBy
    ) {
        return ResponseEntity.ok(carService.findAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<CarResponse> createCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.addCar(carDTO));
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<CarResponse> findCarByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(carService.findByLicensePlate(licensePlate));
    }

    @PutMapping("/{licensePlate}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable String licensePlate, @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(licensePlate, carDTO));
    }

    @DeleteMapping("/{licensePlate}")
    public ResponseEntity<MessageResponse> deleteCar(@PathVariable String licensePlate) {
        try {
            carService.deleteCar(licensePlate);
            return ResponseEntity.ok(new MessageResponse("successfully delete", LocalDateTime.now()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), LocalDateTime.now()));
        }
    }
}
