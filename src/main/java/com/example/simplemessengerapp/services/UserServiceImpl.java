package com.example.simplemessengerapp.services;

import com.example.simplemessengerapp.dto.UserDto;
import com.example.simplemessengerapp.entities.User;
import com.example.simplemessengerapp.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserByUsername(String name) {
        Optional<User> userOptional = repository.getUserByName(name);
        if (userOptional.isEmpty()) {
            throw new NoSuchElementException("User not found");
        }
        return userOptional.get();
    }

    @Override
    @Transactional
    public User registerNewUser(User user) {
        if (repository.getUserByName(user.getName()).isPresent())
            return repository.getUserByName(user.getName()).get();
        return repository.save(user);
    }

    @Override
    public User fromDto(UserDto userDto) {
        if (repository.getUserByName(userDto.getName()).isEmpty()) {
            User user = new User();
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            return user;
        }
        return repository.getUserByName(userDto.getName()).get();
    }

    @Override
    public UserDto toDto(User user) {
        return null;
    }

}
