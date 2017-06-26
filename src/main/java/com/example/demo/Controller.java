package com.example.demo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    @Autowired private UserRepo userRepo;

    @RequestMapping("/")
    public String index(){
        return "welcome to AuthTest";
    }
/*
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "hello world";
    }*/

    @RequestMapping(value = "/api/sayhello", method = RequestMethod.GET)
    public String sayHello(){
        return "I am saying hello";
    }

    @RequestMapping(value = "/api/sayhi", method = RequestMethod.GET)
    public String sayHi(){
       return SecurityContextHolder.getContext().getAuthentication().getName();

//        return "I am saying hi";
    }

    @RequestMapping(value = "/api/saybye", method = RequestMethod.GET)
    public String saybye(){
        return "I am saying bye";
    }

    @RequestMapping(value = "/insertdata", method = RequestMethod.GET)
    public UserEntity insertData(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("yaoyuannie");
        userEntity.setPassword("123");
        userRepo.save(userEntity);
        return userEntity;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8080/logout",null,Void.class);
        return "logged out";
    }
}
