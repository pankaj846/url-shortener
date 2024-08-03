package com.pankaj846.urlshortener.service;

import com.google.common.hash.Hashing;
import com.pankaj846.urlshortener.model.Url;
import com.pankaj846.urlshortener.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private static final Logger logger = LoggerFactory.getLogger(UrlShortenerServiceImpl.class);

    @Autowired
    UrlRepository urlRepository;

    @Override
    public Url createShortUrl(String originalUrl) {
        LocalDateTime time = LocalDateTime.now();
        Url url = null;

        try {
            // Use Google hashing
            String shortUrl = Hashing.murmur3_32()
                    .hashString(originalUrl.concat(time.toString()), StandardCharsets.UTF_8)
                    .toString();

            // Save to DB
            url = new Url(originalUrl, shortUrl, time);
            saveUrl(url);

            logger.info("Successfully created short URL: {} for original URL: {}", shortUrl, originalUrl);

        } catch (Exception e) {
            logger.error("Failed to create short URL for original URL: {}", originalUrl, e);
            // Handle or rethrow the exception as needed
            throw new RuntimeException("Error occurred while creating short URL", e);
        }

        return url;
    }

    @Override
    public Url saveUrl(Url url) {
        Url savedUrl = null;

        try {
            savedUrl = urlRepository.save(url);
            logger.info("URL saved successfully: {}", url);

        } catch (Exception e) {
            logger.error("Failed to save URL: {}", url, e);

            throw new RuntimeException("Error occurred while saving URL", e);
        }

        return savedUrl;
    }

    @Override
    public Url getOriginalUrl(String shortUrl) {
        Url url = null;

        try {
            url = urlRepository.findByShortUrl(shortUrl);
            if (url != null) {
                logger.info("Found original URL: {} for short URL: {}", url.getOriginalUrl(), shortUrl);
            } else {
                logger.warn("No original URL found for short URL: {}", shortUrl);
            }

        } catch (Exception e) {
            logger.error("Failed to retrieve original URL for short URL: {}", shortUrl, e);

            throw new RuntimeException("Error occurred while retrieving original URL", e);
        }

        return url;
    }

    @Override
    public void deleteShortUrl(String shortUrl) {
        try {
            // Implement deletion logic if needed
            logger.info("Attempting to delete short URL: {}", shortUrl);

             urlRepository.deleteByShortUrl(shortUrl);

            logger.info("Successfully deleted short URL: {}", shortUrl);

        } catch (Exception e) {
            logger.error("Failed to delete short URL: {}", shortUrl, e);

            throw new RuntimeException("Error occurred while deleting short URL", e);
        }
    }
}