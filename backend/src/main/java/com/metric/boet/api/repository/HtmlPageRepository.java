package com.metric.boet.api.repository;

import com.metric.boet.api.entity.HtmlPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HtmlPageRepository extends JpaRepository<HtmlPage, Long> {

    Boolean existsByUrl(String url);
    Optional<HtmlPage> getByUrl(String url);
}
