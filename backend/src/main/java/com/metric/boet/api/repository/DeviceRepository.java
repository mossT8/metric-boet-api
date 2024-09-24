package com.metric.boet.api.repository;

import com.metric.boet.api.entity.Device;
import com.metric.boet.api.util.repo.IAbstractTrackedDataBeanRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends IAbstractTrackedDataBeanRepository<Device> {
    Optional<Device> findByUuid(String uid);

    Boolean existsByToken(String token);

    @Query(value = "SELECT dev FROM Device dev ORDER BY dev.createdAt DESC")
    Optional<Device> findLastCreated();
}
