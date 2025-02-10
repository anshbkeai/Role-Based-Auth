package com.role.role.Repositry;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.role.role.Pojos.Rider;
import java.util.List;




public interface RiderRepo extends MongoRepository<Rider,String>{

    Optional<Rider>  findByEmail(String email);
    
} 
