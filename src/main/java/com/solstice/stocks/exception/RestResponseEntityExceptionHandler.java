package com.solstice.stocks.exception;

import java.io.IOException;
import java.text.ParseException;
import org.springframework.aop.AopInvocationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {IOException.class})
  protected ResponseEntity<Object> handleJsonError(Exception ex, WebRequest request) {
    String bodyOfResponse = "<h1>ERROR:</h1>\n "
        + "<p>Could not deserialize JSON document.</p>";
    return handleExceptionInternal(
        ex,
        bodyOfResponse,
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler(value = {ParseException.class})
  protected ResponseEntity<Object> handleBadDate(Exception ex, WebRequest request) {
    String bodyOfResponse = "<h1>ERROR:</h1>\n "
        + "<p>Improper date format provided. Needed: \"yyyy-MM-dd\"</p>";
    return handleExceptionInternal(
        ex,
        bodyOfResponse,
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler(value = {AopInvocationException.class})
  protected ResponseEntity<Object> handleEmptyDataset(Exception ex, WebRequest request) {
    String bodyOfResponse = "<h1>ERROR:</h1>\n"
        + "<p>Symbol table returned null value when searching for given symbol.</p>\n"
        + "<p>Make sure the symbol table has been populated.</p>";
    return handleExceptionInternal(
        ex,
        bodyOfResponse,
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }

}
