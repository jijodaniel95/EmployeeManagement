package com.company.employeemanagement.controller;

import com.company.employeemanagement.exception.ErrorResponse;
import com.company.employeemanagement.exception.NotFoundException;
import com.company.employeemanagement.exception.UserAlreadyExists;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(NotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Resource not found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        logger.error("Resource not found exception occurred. Details: {}", ex.getMessage());
        logger.debug("Resource not found - Stack trace:", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        logger.info("Returning NOT_FOUND response with error message: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    @ApiResponse(responseCode = "409", description = "User already exists",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExists ex) {
        logger.error("User already exists exception occurred. Details: {}", ex.getMessage());
        logger.debug("User already exists - Stack trace:", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        logger.info("Returning CONFLICT response with error message: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NullPointerException.class)
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))})
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        logger.error("Critical - Unexpected NullPointerException occurred. Details: {}", ex.getMessage());
        logger.debug("NullPointerException - Stack trace:", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please contact system administrator."
        );
        logger.info("Returning INTERNAL_SERVER_ERROR response");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
