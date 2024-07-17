package com.pankaj846.urlshortener.repository;

import com.pankaj846.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    public Url findByShortUrl(String longUrl);
}
