package com.pankaj846.urlshortener.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HealthCheckScheduler {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 900000) // 15 minutes in milliseconds
    public void checkHealth() {
        String url = "https://url-shortener-pankaj.onrender.com/health-check";
        restTemplate.getForObject(url, String.class);
    }
}
