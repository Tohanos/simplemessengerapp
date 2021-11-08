package com.example.simplemessengerapp.services;

import com.example.simplemessengerapp.dto.UserDto;
import com.example.simplemessengerapp.entities.User;

public interface UserService {
    User getUserByUsername(String name);

    User registerNewUser(User user);

    User fromDto(UserDto userDto);

    UserDto toDto(User user);

}
