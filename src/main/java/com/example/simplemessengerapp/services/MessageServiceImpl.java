package com.example.simplemessengerapp.services;

import com.example.simplemessengerapp.dto.MessageDto;
import com.example.simplemessengerapp.entities.Message;
import com.example.simplemessengerapp.entities.User;
import com.example.simplemessengerapp.repositories.MessageRepository;
import com.example.simplemessengerapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public List<Message> getMessagesByUser(User user) {
        return messageRepository.findAllByUser(user);
    }

    @Override
    public List<Message> getMessagesByUser(User user, int maxNum) {
        return messageRepository.findTopByOrderByDtDesc(user, maxNum);
    }

    @Override
    public void storeMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message fromDto(MessageDto messageDto) {
        return null;
    }

    @Override
    public MessageDto toDto(Message message) {
        return null;
    }
}
