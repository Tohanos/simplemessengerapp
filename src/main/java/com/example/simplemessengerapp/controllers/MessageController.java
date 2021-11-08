package com.example.simplemessengerapp.controllers;

import com.example.simplemessengerapp.services.MessageService;
import com.example.simplemessengerapp.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final UserService userService;
    private final MessageService messageService;

    public MessageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }


}
