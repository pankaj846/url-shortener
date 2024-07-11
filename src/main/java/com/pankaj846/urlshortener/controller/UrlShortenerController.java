package com.pankaj846.urlshortener.controller;

import com.pankaj846.urlshortener.entity.Url;
import com.pankaj846.urlshortener.service.UrlShortenerService;
import com.pankaj846.urlshortener.service.UrlShortenerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerServiceImpl urlShortenerService;

    @GetMapping("/url/id")
    public Optional<Url> getUrlById(Long id){
        return urlShortenerService.getUrlById(id);
    }

    @PostMapping("/saveUrl")
    public Url saveUrl(@Validated  @RequestBody Url url){
        System.out.println(url.toString());
        return urlShortenerService.saveUrl(url);
    }

}
