package com.tszyi.springbootdemo.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

  private static final Logger LOG = LogManager.getLogger(ApplicationExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleAllExceptions(Exception e) {
    System.out.println("伺服器出錯RR");
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("伺服器出錯RR");
  }

  @ExceptionHandler(SqlOperationException.class)
  public ResponseEntity handleSqlOperationException(SqlOperationException e) {
    System.out.println("SQL 操作出錯RRR");
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SQL 操作出錯RRR");
  }

}
