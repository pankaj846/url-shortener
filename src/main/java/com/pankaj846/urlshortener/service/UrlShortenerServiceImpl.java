package com.pankaj846.urlshortener.service;

import com.google.common.hash.Hashing;
import com.pankaj846.urlshortener.entity.Url;
import com.pankaj846.urlshortener.entity.UrlDto;
import com.pankaj846.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService{

    @Autowired
    UrlRepository urlRepository;

    public UrlDto createShortUrl(String originalUrl) {
        LocalDateTime time = LocalDateTime.now();

        // use google hasing
        String shortUrl = Hashing.murmur3_32()
                .hashString(originalUrl.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();

        UrlDto urlDto = new UrlDto(originalUrl, shortUrl);

        // save to DB
        Url url = new Url(originalUrl, shortUrl, time);
        saveUrl(url);

        return urlDto;
    }

    @Override
    public Url saveUrl(Url url) {
        return urlRepository.save(url);
    }

    @Override
    public UrlDto getOriginalUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl);
        return convertUrlToUrlDto(url);
    }

    @Override
    public void deleteShortUrl(String shortUrl) {

    }

    private UrlDto convertUrlToUrlDto(Url url){
        UrlDto urlDto = new UrlDto(url.getShortUrl(), url.getOriginalUrl());
        return urlDto;
    }


}
