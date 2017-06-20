package com.example.demo;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JdbcUserDetailService implements UserDetailsService{
    private UserRepo userRepo;

    public JdbcUserDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String name){
        UserEntity user = userRepo.findByUsername(name);
        if(user == null){
            return null;
        }
        return new User(user.getUsername(),user.getPassword(),true,true,true,true, AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
