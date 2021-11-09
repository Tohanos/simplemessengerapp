package com.example.simplemessengerapp.services;

import com.example.simplemessengerapp.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageParserServiceImpl implements MessageParserService{

    private final MessageDbService service;
    private final UserDbService userDbService;

    public MessageParserServiceImpl(MessageDbService service, UserDbService userDbService) {
        this.service = service;
        this.userDbService = userDbService;
    }

    @Override
    public Optional<List<String>> parseMessage(MessageDto messageDto) {
        String[] strings = messageDto.getMessage().split(" ");
        switch (strings[0]) {
            case "History":
                return Optional.of(service
                        .getMessages(Integer.parseInt(strings[1]))
                        .stream()
                        .map(message->message.getText())
                        .collect(Collectors.toList()));
        }
        return Optional.empty();
    }
}
