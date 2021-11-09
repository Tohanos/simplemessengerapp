package com.example.simplemessengerapp.controllers;

import com.example.simplemessengerapp.dto.TokenDto;
import com.example.simplemessengerapp.dto.UserDto;
import com.example.simplemessengerapp.entities.User;
import com.example.simplemessengerapp.services.TokenAuthenticationService;
import com.example.simplemessengerapp.services.UserDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final UserDbService userDbService;
    private final TokenAuthenticationService tokenAuthenticationService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserDbService userDbService, TokenAuthenticationService tokenAuthenticationService) {
        this.userDbService = userDbService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @PostMapping("/user")
    public ResponseEntity<TokenDto> userRegister(@RequestBody UserDto userDto) {
        logger.info(userDto.toString());
        User user;
        String token;
        try {
            if (userDbService.checkPassword(userDto.getName(), userDto.getPassword())) {
                user = userDbService.getUserByUsername(userDto.getName());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (NoSuchElementException e) {
            user = userDbService.registerNewUser(userDbService.fromDto(userDto));
        }
        token = tokenAuthenticationService.generateToken(user.getName());
        return ResponseEntity.ok(new TokenDto(token));

    }


}
