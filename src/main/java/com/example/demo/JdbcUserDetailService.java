package com.example.demo;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JdbcUserDetailService implements UserDetailsService{
    private UserRepo userRepo;

    public JdbcUserDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String name){
        try{
            UserEntity user = userRepo.findByUsername(name);
            if(user == null){
                return null;
            }
            return new User(user.getUsername(),user.getPassword(),true,true,true,true, AuthorityUtils.createAuthorityList("ROLE_USER"));
        }catch (Exception ex){
            throw new UsernameNotFoundException("User NOT Found");
        }

    }
}
