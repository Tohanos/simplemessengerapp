package com.example.simplemessengerapp.controllers;

import com.example.simplemessengerapp.dto.MessageDto;
import com.example.simplemessengerapp.services.MessageDbService;
import com.example.simplemessengerapp.services.MessageParserService;
import com.example.simplemessengerapp.services.TokenAuthenticationService;
import com.example.simplemessengerapp.services.UserDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final UserDbService userDbService;
    private final MessageDbService messageDbService;
    private final MessageParserService messageParserService;
    private final TokenAuthenticationService tokenAuthenticationService;

    private final static String MESSAGE_HEADER = "Bearer";

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    public MessageController(UserDbService userDbService, MessageDbService messageDbService, MessageParserService messageParserService, TokenAuthenticationService tokenAuthenticationService) {
        this.userDbService = userDbService;
        this.messageDbService = messageDbService;
        this.messageParserService = messageParserService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @PostMapping("/message")
    public ResponseEntity<String> receiveMessage(@RequestHeader(MESSAGE_HEADER) String auth, @RequestBody MessageDto messageDto) {
        if (tokenAuthenticationService.checkToken(messageDto.getName(), auth)) {
            if (messageParserService.parseMessage(messageDto).isPresent()) {
                return ResponseEntity.ok(messageParserService.parseMessage(messageDto).get().toString());
            }
            messageDbService.storeMessage(messageDbService.fromDto(messageDto));
            return ResponseEntity.ok("Alright");
        }
        return ResponseEntity.status(401).body("Bad token");
    }

}
