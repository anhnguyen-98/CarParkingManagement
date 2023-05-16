package com.mock.CarParkingManagement.controller;

import com.mock.CarParkingManagement.model.dto.ParkingLotDTO;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.MessageResponse;
import com.mock.CarParkingManagement.model.response.ParkingLotResponse;
import com.mock.CarParkingManagement.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/parkingLot")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping("/all")
    public ResponseEntity<CustomPage<ParkingLotResponse>> findAllParkingLots(
            @RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "5", name = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "parkId", name = "sortBy") String sortBy
    ) {
        return ResponseEntity.ok(parkingLotService.findAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<ParkingLotResponse> createParkingLot(@RequestBody ParkingLotDTO parkingLotDTO) {
        return ResponseEntity.ok(parkingLotService.addParkingLot(parkingLotDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLotResponse> findParkingLotById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingLotService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLotResponse> updateParkingLot(@PathVariable Long id, @RequestBody ParkingLotDTO parkingLotDTO) {
        return ResponseEntity.ok(parkingLotService.updateParkingLot(id, parkingLotDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteParkingLot(@PathVariable Long id) {
        try {
            parkingLotService.deleteParkingLot(id);
            return ResponseEntity.ok(new MessageResponse("successfully delete", LocalDateTime.now()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), LocalDateTime.now()));
        }
    }
}
