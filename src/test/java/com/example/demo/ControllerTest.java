package com.example.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerTest {
   public RestTemplate restTemplate = new RestTemplate();

   @Autowired private UserRepo userRepo;

    @Test
    public void indexTest(){
        UserEntity user = new UserEntity();
        user.setPassword("123456");
        user.setUsername("lewis");
        userRepo.save(user);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080", HttpMethod.GET,new HttpEntity<String>(createHeaders("lewis","12356")),String.class);
                //getForEntity("http://localhost:8080",new HttpEntity<T>(createHeaders("lewis","123456")));

        assertEquals(response.getStatusCode(),"401");
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
