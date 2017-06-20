package com.example.demo;


import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
