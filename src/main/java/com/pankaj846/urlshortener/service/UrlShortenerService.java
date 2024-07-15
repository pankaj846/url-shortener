package com.pankaj846.urlshortener.service;

import com.pankaj846.urlshortener.entity.Url;
import com.pankaj846.urlshortener.entity.UrlDto;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface UrlShortenerService {

    public Url createShortUrl(String originalUrl);

    public Url saveUrl(Url url);

    public Url getOriginalUrl(String shortUrl);

    public void deleteShortUrl(String shortUrl);

}
