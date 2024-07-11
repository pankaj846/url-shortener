package com.pankaj846.urlshortener.service;

import com.pankaj846.urlshortener.entity.Url;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UrlShortenerService {

    public Url saveUrl(Url url);

    public Optional<Url> getUrlById(Long id);

    public void deleteUrlById(Long iPd);


}
