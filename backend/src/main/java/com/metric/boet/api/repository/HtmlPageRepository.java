package com.metric.boet.api.repository;

import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.util.repo.IAbstractTrackedDataBeanRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HtmlPageRepository extends IAbstractTrackedDataBeanRepository<HtmlPage> {

    Boolean existsByUrl(String url);

    Optional<HtmlPage> findByUrl(String url);
}
