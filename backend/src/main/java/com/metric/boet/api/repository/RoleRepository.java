package com.metric.boet.api.repository;

import java.util.Optional;

import com.metric.boet.api.models.ERole;
import com.metric.boet.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
