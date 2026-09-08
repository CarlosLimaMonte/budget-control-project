package com.portfolio.BudgetControl.advice;

import com.portfolio.BudgetControl.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFound(NotFoundException notFoundException){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
  }


}
