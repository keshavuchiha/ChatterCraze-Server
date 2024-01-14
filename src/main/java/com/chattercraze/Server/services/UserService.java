package com.chattercraze.Server.services;

import com.chattercraze.Server.Models.User;
import com.chattercraze.Server.configs.ControllerException;
import com.chattercraze.Server.dao.UserRepository;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Transactional
    public String login(String email,String password) throws ControllerException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<User> userPage = userRepository.findByEmail(email,pageable);
        if(userPage.isEmpty() || userPage.getContent().isEmpty()){
            throw new ControllerException("User not Found", HttpStatus.NOT_FOUND);
        }
        User user = userPage.getContent().get(0);
        if(BCrypt.checkpw(password,user.getPassword())){
//            Jwts.builder().signWith()
        } else{
            throw new ControllerException("Password is incorrect", HttpStatus.UNAUTHORIZED);
        }
        return "";
    }
}
