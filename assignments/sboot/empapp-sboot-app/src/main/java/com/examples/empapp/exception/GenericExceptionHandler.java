package com.examples.empapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.examples.empapp.model.ResponseMessage;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ResponseMessage> handleApplicationException(Exception e) {
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Failure");
        response.setMessage("App Error: " + e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGenericException(Exception e) {
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Failure");
        response.setMessage("Server Error: " + e.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }
}
