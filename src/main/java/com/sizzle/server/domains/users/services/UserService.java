package com.sizzle.server.domains.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sizzle.server.domains.users.dtos.UserDto;
import com.sizzle.server.domains.users.entities.User;
import com.sizzle.server.domains.users.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getEmail());
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
