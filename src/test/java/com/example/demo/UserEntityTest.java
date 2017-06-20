package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTest {
    @Autowired private UserRepo userRepo;

    @Test
    public void findByUserNameTest(){
        UserEntity user = new UserEntity();
        user.setUsername("sfaf");
        user.setPassword("12345");
        userRepo.save(user);
        UserEntity saved = userRepo.findByUsername("sfaf");
        assertEquals(user.getUsername(),saved.getUsername());
    }

}
