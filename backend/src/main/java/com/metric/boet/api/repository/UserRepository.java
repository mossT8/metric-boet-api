package com.metric.boet.api.repository;

import java.util.*;
import java.util.Optional;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.util.repo.IAbstractDataBeanRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IAbstractDataBeanRepository<User> {

    List<User> findAllByOrderByAccountCodeDesc();

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
