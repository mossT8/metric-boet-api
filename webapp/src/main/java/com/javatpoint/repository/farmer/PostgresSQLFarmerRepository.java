package com.javatpoint.repository.farmer;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PostgresSQLFarmerRepository implements FarmerRepository {

    // Add custom methods here
}
