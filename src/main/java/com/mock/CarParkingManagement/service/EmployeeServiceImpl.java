package com.mock.CarParkingManagement.service;

import com.mock.CarParkingManagement.model.dto.EmployeeDTO;
import com.mock.CarParkingManagement.exception.EntityNotFoundException;
import com.mock.CarParkingManagement.exception.UsernameExistedException;
import com.mock.CarParkingManagement.model.entity.Employee;
import com.mock.CarParkingManagement.model.others.CustomPage;
import com.mock.CarParkingManagement.model.response.EmployeeResponse;
import com.mock.CarParkingManagement.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomPage<EmployeeResponse> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = employeeRepository.findAll(pageable);
        List<Employee> employees = new ArrayList<>();
        if (pagedResult.hasContent()) {
            employees = pagedResult.getContent();
        }
        List<EmployeeResponse> employeeResponseList = employees
                .stream().map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());
        CustomPage<EmployeeResponse> employeeResponsePage = new CustomPage<>();
        employeeResponsePage.setContent(employeeResponseList);
        employeeResponsePage.setCurrentPage(pagedResult.getNumber());
        employeeResponsePage.setTotalItems(pagedResult.getTotalElements());
        employeeResponsePage.setTotalPages(pagedResult.getTotalPages());
        return employeeResponsePage;
    }

    @Override
    public EmployeeResponse findById(Long id) {
        return modelMapper.map(employeeRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Employee with id = " + id + " not existed");
        }), EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByUsername(employeeDTO.getUsername())) {
            throw new UsernameExistedException("Username " + employeeDTO.getUsername() + " already existed");
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return modelMapper.map(employeeRepository.save(employee), EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Employee with id = " + id + " not existed");
        });
        Employee employeeToUpdate = modelMapper.map(employeeDTO, Employee.class);
        employeeToUpdate.setEmployeeId(employee.getEmployeeId());
        employeeToUpdate.setUsername(employee.getUsername());
        return modelMapper.map(employeeRepository.save(employeeToUpdate), EmployeeResponse.class);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Employee with id = " + id + " not existed");
        });
        employeeRepository.deleteById(id);
    }
}
