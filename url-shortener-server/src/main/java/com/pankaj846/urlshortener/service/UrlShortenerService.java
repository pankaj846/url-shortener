package com.pankaj846.urlshortener.service;

import com.pankaj846.urlshortener.model.Url;
import org.springframework.stereotype.Service;


@Service
public interface UrlShortenerService {

    public Url createShortUrl(String originalUrl);

    public Url saveUrl(Url url);

    public Url getOriginalUrl(String shortUrl);

    public void deleteShortUrl(String shortUrl);

}
