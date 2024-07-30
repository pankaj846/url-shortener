package com.pankaj846.urlshortener.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_seq_gen")
    @SequenceGenerator(name = "url_seq_gen", sequenceName = "url_seq", allocationSize = 1)
    private Long id;

    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdDate;

    public Url(String originalUrl, String shortUrl, LocalDateTime createdDate) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createdDate = createdDate;
    }
}
