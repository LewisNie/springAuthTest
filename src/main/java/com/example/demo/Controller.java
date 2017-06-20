package com.example.demo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired private UserRepo userRepo;

    @RequestMapping("/")
    public UserEntity index(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("yaoyuannie");
        userEntity.setPassword("123");
        userRepo.save(userEntity);
        return userEntity;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "hello world";
    }
}
