package com.pankaj846.urlshortener.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UrlDto {

    private String shortUrl;
    @NotBlank(message = "url can not be blank and null")
    private String originalUrl;

}
