package com.metric.boet.api.repository;

import java.util.Optional;

import com.metric.boet.api.entity.Device;
import com.metric.boet.api.util.repo.IAbstractDataBeanRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends IAbstractDataBeanRepository<Device> {
    Optional<Device> findByUuid(String uid);

    Boolean existsByToken(String token);

    @Query(value = "SELECT dev FROM Device dev ORDER BY dev.createdAt DESC")
    Optional<Device> findLastCreated();
}
