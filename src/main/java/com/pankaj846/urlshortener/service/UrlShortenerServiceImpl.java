package com.pankaj846.urlshortener.service;

import com.google.common.hash.Hashing;
import com.pankaj846.urlshortener.model.Url;
import com.pankaj846.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService{

    @Autowired
    UrlRepository urlRepository;

    public Url createShortUrl(String originalUrl) {
        LocalDateTime time = LocalDateTime.now();

        // use google hasing
        String shortUrl = Hashing.murmur3_32()
                .hashString(originalUrl.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();

        // save to DB
        Url url = new Url(originalUrl, shortUrl, time);
        saveUrl(url);

        return url;
    }

    @Override
    public Url saveUrl(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public Url getOriginalUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    @Override
    public void deleteShortUrl(String shortUrl) {

    }

}
