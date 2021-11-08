package com.example.simplemessengerapp.services;

import com.example.simplemessengerapp.dto.MessageDto;
import com.example.simplemessengerapp.entities.Message;
import com.example.simplemessengerapp.entities.User;

import java.util.List;

public interface MessageService {

    Message getMessageById(Long id);
    List<Message> getMessagesByUser(User user);
    List<Message> getMessagesByUser(User user, int maxNum);
    void storeMessage(Message message);

    Message fromDto(MessageDto messageDto);
    MessageDto toDto(Message message);

}
