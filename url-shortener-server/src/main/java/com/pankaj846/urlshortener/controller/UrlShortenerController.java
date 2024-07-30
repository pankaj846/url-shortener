package com.pankaj846.urlshortener.controller;

import com.pankaj846.urlshortener.dto.ErrorResponseDTO;
import com.pankaj846.urlshortener.model.Url;
import com.pankaj846.urlshortener.dto.UrlDto;
import com.pankaj846.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.io.IOException;

@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @CrossOrigin(origins = "*")
    @PostMapping("/shorten")
    public ResponseEntity<UrlDto> createShortUrl(@Valid @RequestBody UrlDto urlDto){
        String originalUrl = urlDto.getOriginalUrl();
        Url url = urlShortenerService.createShortUrl(originalUrl);
        UrlDto responseDto = new UrlDto(url.getShortUrl(), url.getOriginalUrl());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{shortUrl}")
    public ResponseEntity<ErrorResponseDTO> redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {

        // get Url object from DB
        Url url = urlShortenerService.getOriginalUrl(shortUrl);
        if(url == null){
            ErrorResponseDTO errorResponseDto = new ErrorResponseDTO("Url not exist", 400);
            return new ResponseEntity<ErrorResponseDTO>(errorResponseDto, HttpStatus.OK);

        }

        response.sendRedirect(url.getOriginalUrl());
        return null;
    }

}
