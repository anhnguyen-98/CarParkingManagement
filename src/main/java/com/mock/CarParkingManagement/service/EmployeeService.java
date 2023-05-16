package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.EmployeeDTO;
import com.mock.CarParkingManagement.model.entity.Employee;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.EmployeeResponse;

public interface EmployeeService {
    CustomPage<EmployeeResponse> findAll(Integer pageNo, Integer pageSize, String sortBy);

    EmployeeResponse findById(Long id);

    EmployeeResponse addEmployee(EmployeeDTO employeeDTO);

    EmployeeResponse updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
