package com.diaryapp.service;

import com.diaryapp.model.User;
import com.diaryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public abstract User getById(String id);

    public abstract void init();
}