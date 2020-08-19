package com.geekbrains.july.market.utils;

import com.geekbrains.july.market.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(ProductNotFoundException.class)
        protected ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exc) {
            return new ResponseEntity<>(exc.getMessage() + " Ololo", HttpStatus.NOT_FOUND);
        }
    }
