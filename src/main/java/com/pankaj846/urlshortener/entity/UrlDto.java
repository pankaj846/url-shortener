package com.pankaj846.urlshortener.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UrlDto {

    private String shortUrl;
    private String originalUrl;

}
