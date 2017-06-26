package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableGlobalAuthentication
@ComponentScan
public class JdbcSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService(){
        return new JdbcUserDetailService(userRepo);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("yao");
        user.setPassword("123");
        userRepo.save(user);
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
