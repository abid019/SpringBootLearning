package com.abid123.module1introduction;

import com.abid123.module1introduction.entities.User;
import com.abid123.module1introduction.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void tokenTest(){
        User user =  new User(4L,"abid@gmail.com","abid1234");

        String token = jwtService.generateJwtToken(user);

        System.out.println(token);

        Long id = jwtService.getUserIdFromJwtToken(token);

        System.out.println(id);
    }

}
