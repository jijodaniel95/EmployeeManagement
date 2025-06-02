package com.company.employeemanagement.service.impl;

import com.company.employeemanagement.dto.EmployeeDTO;
import com.company.employeemanagement.dto.SalaryDTO;
import com.company.employeemanagement.entity.*;
import com.company.employeemanagement.exception.NotFoundException;
import com.company.employeemanagement.exception.UserAlreadyExists;
import com.company.employeemanagement.mapper.EmployeeMapper;
import com.company.employeemanagement.mapper.SalaryMapper;
import com.company.employeemanagement.repository.DepartmentRepository;
import com.company.employeemanagement.repository.EmployeeRepository;
import com.company.employeemanagement.repository.RoleRepository;
import com.company.employeemanagement.repository.SalaryRepository;
import com.company.employeemanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final SalaryRepository salaryRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                             RoleRepository roleRepository,
                             DepartmentRepository departmentRepository,
                             SalaryRepository salaryRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.salaryRepository = salaryRepository;
        logger.info("EmployeeServiceImpl initialized with required repositories");
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        logger.debug("Fetching all employees from database");
        List<Employee> employeesList = employeeRepository.findAll();
        logger.info("Found {} employees in database", employeesList.size());

        if (!employeesList.isEmpty()) {
            List<EmployeeDTO> employeeDTOList = EmployeeMapper.toDTOList(employeesList);
            logger.debug("Successfully mapped {} employees to DTOs", employeeDTOList.size());
            return employeeDTOList;
        }
        logger.info("No employees found in database");
        return new ArrayList<>();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesByPagination(int pageNo, int pageSize) {
        logger.debug("Fetching employees with pagination - page: {}, size: {}", pageNo, pageSize);
        List<Employee> employeesList = employeeRepository.findAll(PageRequest.of(pageNo, pageSize)).getContent();
        logger.info("Found {} employees for page {}", employeesList.size(), pageNo);

        if (!employeesList.isEmpty()) {
            List<EmployeeDTO> employeeDTOList = EmployeeMapper.toDTOList(employeesList);
            logger.debug("Successfully mapped {} employees to DTOs for page {}", employeeDTOList.size(), pageNo);
            return employeeDTOList;
        }
        logger.info("No employees found for page {}", pageNo);
        return new ArrayList<>();
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        logger.debug("Fetching employee with ID: {}", id);
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            logger.error("Employee not found with ID: {}", id);
            throw new NotFoundException("Employee not found with id: " + id);
        }

        EmployeeDTO employeeDTO = EmployeeMapper.toDTO(employee.get());
        logger.info("Successfully retrieved employee: {} {}", employeeDTO.getFirstName(), employeeDTO.getLastName());
        return employeeDTO;
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        logger.debug("Starting to save employee with email: {}", employeeDTO.getEmail());

        // Check if employee exists
        Optional<Employee> employeePresent = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (employeePresent.isPresent()) {
            logger.error("Cannot save employee - email already exists: {}", employeeDTO.getEmail());
            throw new UserAlreadyExists("Employee already exists with email: " + employeeDTO.getEmail());
        }

        // Map and set basic employee details
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        logger.debug("Mapped employee DTO to entity");

        // Set role if provided
        if (employeeDTO.getRole() != null && employeeDTO.getRole().getRoleId() != null) {
            logger.debug("Setting role for employee. Role ID: {}", employeeDTO.getRole().getRoleId());
            Optional<Role> role = roleRepository.findById(employeeDTO.getRole().getRoleId());
            role.ifPresent(r -> {
                employee.setRole(r);
                logger.debug("Role set successfully: {}", r.getRoleName());
            });
        }

        // Set department if provided
        if (employeeDTO.getDepartment() != null && employeeDTO.getDepartment().getDeptId() != null) {
            logger.debug("Setting department for employee. Department ID: {}", employeeDTO.getDepartment().getDeptId());
            Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartment().getDeptId());
            department.ifPresent(d -> {
                employee.setDepartment(d);
                logger.debug("Department set successfully: {}", d.getDeptName());
            });
        }

        // Save employee
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Employee saved successfully with ID: {}", savedEmployee.getEmpId());

        // Handle salary information
        if (employeeDTO.getSalaries() != null && !employeeDTO.getSalaries().isEmpty()) {
            logger.debug("Processing {} salary records for employee", employeeDTO.getSalaries().size());

            for (SalaryDTO salaryDTO : employeeDTO.getSalaries()) {
                LocalDate effectiveFrom = (salaryDTO.getEffectiveFrom() != null) ? salaryDTO.getEffectiveFrom() : LocalDate.now();
                SalaryId potentialSalaryId = new SalaryId(savedEmployee.getEmpId(), effectiveFrom);

                if (salaryRepository.existsById(potentialSalaryId)) {
                    logger.error("Salary record already exists for employee {} on date {}", savedEmployee.getEmpId(), effectiveFrom);
                    throw new UserAlreadyExists("Salary record for employee " + savedEmployee.getEmpId() + " already exists for date " + effectiveFrom);
                }

                Salary newSalary = SalaryMapper.toEntity(salaryDTO);
                logger.debug("Processing salary record: {}", salaryDTO);
                savedEmployee.addSalary(newSalary);
                newSalary.setEmployee(savedEmployee);
                salaryRepository.save(newSalary);
                logger.debug("Salary record saved successfully for date: {}", effectiveFrom);
            }

            employeeRepository.save(savedEmployee);
            logger.info("All salary records processed and saved successfully");
        }

        EmployeeDTO resultDTO = EmployeeMapper.toDTO(savedEmployee);
        logger.info("Employee creation completed successfully. Returning DTO with ID: {}", resultDTO.getEmpId());
        return resultDTO;
    }
}
