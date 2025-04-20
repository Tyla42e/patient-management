package com.pm.patientservice.exception;

public class EmailAlreadyExisitsException extends RuntimeException {
    
    public EmailAlreadyExisitsException(String message) {
        super(message);
    }
}
