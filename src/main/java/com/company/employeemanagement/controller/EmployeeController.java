package com.company.employeemanagement.controller;

import com.company.employeemanagement.dto.EmployeeDTO;
import com.company.employeemanagement.exception.ErrorResponse;
import com.company.employeemanagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Retrieves a list of all employees in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all employees",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.info("Received request to get all employees");
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        logger.info("Returning {} employees", employees.size());
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employees with pagination",
              description = "Retrieves a paginated list of employees")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated employees",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid page number or size",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/getAllByPagination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByPagination(
            @Parameter(description = "Page number (0-based)", required = true) @RequestParam int pageNo,
            @Parameter(description = "Number of items per page", required = true) @RequestParam int pageSize) {
        logger.info("Received request to get employees with pagination - page: {}, size: {}", pageNo, pageSize);
        List<EmployeeDTO> employees = employeeService.getAllEmployeesByPagination(pageNo, pageSize);
        logger.info("Returning {} employees for page {}", employees.size(), pageNo);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID", description = "Retrieves an employee by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the employee",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeDTO.class))),
        @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> getEmployeeById(
            @Parameter(description = "ID of the employee to retrieve", required = true)
            @PathVariable Integer id) {
        logger.info("Received request to get employee with ID: {}", id);
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        logger.info("Found employee: {} {}", employee.getFirstName(), employee.getLastName());
        return ResponseEntity.ok(employee);
    }

    @Operation(summary = "Create new employee",
              description = "Creates a new employee with the provided information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully created the employee",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid employee data provided",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "409", description = "Employee with email already exists",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> saveEmployee(
            @Parameter(description = "Employee information", required = true)
            @RequestBody EmployeeDTO employeeDTO) {
        logger.info("Received request to save employee with email: {}", employeeDTO.getEmail());
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employeeDTO);
        logger.info("Successfully saved employee with ID: {}", savedEmployee.getEmpId());
        return ResponseEntity.ok(savedEmployee);
    }
}
