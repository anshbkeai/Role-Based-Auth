package com.role.role.Repositry;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.role.role.Pojos.Driver;

public interface DriverRepo extends  MongoRepository<Driver , String>{

    Optional<Driver> findByEmail(String  email);
}
