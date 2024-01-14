package com.chattercraze.Server.Controllers;

import com.chattercraze.Server.Models.User;
import com.chattercraze.Server.configs.ControllerException;
import com.chattercraze.Server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/login")
    ResponseEntity<Object> login(@RequestBody Map<String,Object> requestBody){
        try{
            String email=requestBody.get("email").toString();
            String password=requestBody.get("password").toString();
            userService.login(email,password);
            return new ResponseEntity<>("NOT IMPLEMENTED", HttpStatus.NOT_IMPLEMENTED);
        } catch (ControllerException ex){
            return new ResponseEntity<>(ex.getMessage(),ex.getHttpStatus());
        } catch (Exception ex){
            return ControllerException.throwGenericError();
        }

    }
}
