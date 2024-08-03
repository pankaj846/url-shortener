package com.pankaj846.urlshortener.controller;

import com.pankaj846.urlshortener.dto.ErrorResponseDTO;
import com.pankaj846.urlshortener.model.Url;
import com.pankaj846.urlshortener.dto.UrlDto;
import com.pankaj846.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.io.IOException;

@RestController
public class UrlShortenerController {

    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerController.class);

    @Autowired
    UrlShortenerService urlShortenerService;

    @CrossOrigin(origins = "*")
    @PostMapping("/shorten")
    public ResponseEntity<UrlDto> createShortUrl(@Valid @RequestBody UrlDto urlDto) {
        try {
            logger.info("Received request to create short URL for: {}", urlDto.getOriginalUrl());

            String originalUrl = urlDto.getOriginalUrl();
            Url url = urlShortenerService.createShortUrl(originalUrl);
            UrlDto responseDto = new UrlDto(url.getShortUrl(), url.getOriginalUrl());

            logger.info("Short URL created successfully: {} -> {}", responseDto.getShortUrl(), responseDto.getOriginalUrl());
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("Failed to create short URL for: {}", urlDto.getOriginalUrl(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{shortUrl}")
    public ResponseEntity<ErrorResponseDTO> redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) {
        try {
            logger.info("Received request to redirect short URL: {}", shortUrl);

            // Get Url object from DB
            Url url = urlShortenerService.getOriginalUrl(shortUrl);
            if (url == null) {
                logger.warn("Short URL not found: {}", shortUrl);
                ErrorResponseDTO errorResponseDto = new ErrorResponseDTO("Url not exist", 400);
                return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
            }

            logger.info("Redirecting to original URL: {}", url.getOriginalUrl());
            response.sendRedirect(url.getOriginalUrl());
            return null; // Sending a redirect will end the response, so this is technically not needed.

        } catch (Exception e) {
            logger.error("Failed to redirect short URL: {}", shortUrl, e);
            ErrorResponseDTO errorResponseDto = new ErrorResponseDTO("Redirect failed", 500);
            return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}