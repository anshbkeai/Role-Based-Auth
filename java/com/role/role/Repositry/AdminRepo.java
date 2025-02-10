package com.role.role.Repositry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.role.role.Pojos.App_User;

public interface AdminRepo extends  MongoRepository<App_User, String>{

}
