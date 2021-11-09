package com.example.simplemessengerapp.services;

import com.example.simplemessengerapp.dto.MessageDto;
import com.example.simplemessengerapp.entities.Message;
import com.example.simplemessengerapp.entities.User;
import com.example.simplemessengerapp.repositories.MessageRepository;
import com.example.simplemessengerapp.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageDbServiceImpl implements MessageDbService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;

    public MessageDbServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.getById(id);
    }

    @Override
    public List<Message> getMessages(User user) {
        return messageRepository.findAllByUser(user);
    }

    @Override
    public List<Message> getMessages(int maxNum) {

        Page<Message> page = messageRepository.findAll(PageRequest.of(0, maxNum, Sort.by(Sort.Direction.DESC, "dt")));
        return page.getContent();
    }

    @Override
    public void storeMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message fromDto(MessageDto messageDto) {
        if (userRepository.getUserByName(messageDto.getName()).isPresent()) {
            Message message = new Message();
            message.setUser(userRepository.getUserByName(messageDto.getName()).get());
            message.setText(messageDto.getMessage());
            message.setDt(Timestamp.valueOf(LocalDateTime.now()));
            return message;
        }
        return null;
    }

    @Override
    public MessageDto toDto(Message message) {
        return new MessageDto(message.getUser().getName(), message.getText());
    }
}
