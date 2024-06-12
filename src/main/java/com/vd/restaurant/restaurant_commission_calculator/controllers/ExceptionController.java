package com.vd.restaurant.restaurant_commission_calculator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vd.restaurant.restaurant_commission_calculator.exceptions.MenuItemNotFoundException;
import com.vd.restaurant.restaurant_commission_calculator.exceptions.OrderNotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<String> handleMenuItemNotFoundException(MenuItemNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
