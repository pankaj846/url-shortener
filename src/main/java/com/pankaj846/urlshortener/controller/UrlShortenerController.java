package com.pankaj846.urlshortener.controller;

import com.pankaj846.urlshortener.entity.Url;
import com.pankaj846.urlshortener.entity.UrlDto;
import com.pankaj846.urlshortener.service.UrlShortenerService;
import com.pankaj846.urlshortener.service.UrlShortenerServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.net.URLDecoder;
import java.util.Optional;

@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping("/urlshortener")
    public UrlDto saveUrl(@Validated  @RequestBody UrlDto urlDto){
        String originalUrl = urlDto.getOriginalUrl();
        return urlShortenerService.createShortUrl(originalUrl);
    }

    @GetMapping("/{shortUrl}")
    public UrlDto getUrl(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) throws IOException {
        UrlDto originalUrl = urlShortenerService.getOriginalUrl(shortUrl);
        httpServletResponse.sendRedirect(originalUrl.getOriginalUrl());
        return null;
    }

}
