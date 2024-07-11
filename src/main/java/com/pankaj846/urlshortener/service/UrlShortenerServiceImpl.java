package com.pankaj846.urlshortener.service;

import com.pankaj846.urlshortener.entity.Url;
import com.pankaj846.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService{

    @Autowired
    UrlRepository urlRepository;

    @Override
    public Url saveUrl(Url url) {
        urlRepository.save(url);
        return url;
    }

    @Override
    public Optional<Url> getUrlById(Long id) {
        return urlRepository.findById(id);
    }

    @Override
    public void deleteUrlById(Long id) {
        urlRepository.deleteById(id);
    }
}
