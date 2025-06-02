package com.company.employeemanagement.exception;

public class ErrorResponse {
    private final int status;
    private final String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
}
