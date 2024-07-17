package com.pankaj846.urlshortener.exception;

import com.pankaj846.urlshortener.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<ErrorResponseDTO>(errorResponseDTO, HttpStatus.BAD_REQUEST);

    }
}
