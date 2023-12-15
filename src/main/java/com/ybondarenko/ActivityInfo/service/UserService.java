package com.ybondarenko.ActivityInfo.service;

import com.ybondarenko.ActivityInfo.entity.User;
import com.ybondarenko.ActivityInfo.exceptions.UserNotFoundException;
import com.ybondarenko.ActivityInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findByName(String userName) {
        return userRepository.findByName(userName);
    }

    public User findById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
