package com.metric.boet.api.repository;
import java.util.*;
import java.util.Optional;

import com.metric.boet.api.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByIdAndUserUsername(Long id, String username);
    List<Device> findByUserUsername(String username);
    Boolean existsByNameAndUserUsername(String name, String username);
    Boolean existsByToken(String token);
}
