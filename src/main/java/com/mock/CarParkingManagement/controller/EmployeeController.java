package com.mock.CarParkingManagement.controller;

import com.mock.CarParkingManagement.model.dto.EmployeeDTO;
import com.mock.CarParkingManagement.model.entity.Employee;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.MessageResponse;
import com.mock.CarParkingManagement.model.response.EmployeeResponse;
import com.mock.CarParkingManagement.service.EmployeeService;
import com.mock.CarParkingManagement.util.password.ValidPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/all")
    public ResponseEntity<CustomPage<EmployeeResponse>> findAllEmployees(
            @RequestParam(defaultValue = "0", name = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "5", name = "pageSize") Integer pageSize,
            @RequestParam(defaultValue = "employeeId", name = "sortBy") String sortBy
    ) {
        return ResponseEntity.ok(employeeService.findAll(pageNo, pageSize, sortBy));
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok(new MessageResponse("successfully delete", LocalDateTime.now()));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage(), LocalDateTime.now()));
        }

    }
}
