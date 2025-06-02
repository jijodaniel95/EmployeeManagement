package com.company.employeemanagement.service.impl;

import com.company.employeemanagement.dto.EmployeeDTO;
import com.company.employeemanagement.entity.Employee;
import com.company.employeemanagement.mapper.EmployeeMapper;
import com.company.employeemanagement.repository.EmployeeRepository;
import com.company.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList=new ArrayList<>();
        List<Employee> employeesList=employeeRepository.findAll();
        if(!employeesList.isEmpty()){
            employeeDTOList=EmployeeMapper.toDTOList(employeesList);
        }
        return employeeDTOList;
    }
}
