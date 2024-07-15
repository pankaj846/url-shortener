package com.pankaj846.urlshortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private int status;
}
