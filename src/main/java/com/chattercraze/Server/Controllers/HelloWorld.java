package com.chattercraze.Server.Controllers;

import com.chattercraze.Server.Models.User;
import com.chattercraze.Server.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorld {
    @Autowired
    private UserRepository userRepository;
    @GetMapping(value = "/v1/healthcheck")
    String sayHi(){
        return "Hello Stranger";
    }
    @GetMapping(value="/v1/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
