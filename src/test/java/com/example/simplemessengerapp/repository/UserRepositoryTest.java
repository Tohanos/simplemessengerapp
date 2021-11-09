package com.example.simplemessengerapp.repository;

import com.example.simplemessengerapp.entities.User;
import com.example.simplemessengerapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllUsers() {
        List<User> users = userRepository.findAll();

        int nOfUsers = 2;
        assertThat(users).hasSize(nOfUsers);
    }

}
