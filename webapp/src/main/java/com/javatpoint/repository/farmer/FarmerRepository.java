package com.javatpoint.repository.farmer;

import com.javatpoint.models.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    // Add custom methods here
}
