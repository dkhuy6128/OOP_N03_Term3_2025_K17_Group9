package com.diaryapp.service;

import com.diaryapp.model.User;
import com.diaryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        if (user == null || user.getId() == null || user.getId().isEmpty() || userRepository.existsById(user.getId())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void init() {
        userRepository.save(new User("user123", "Theo"));
        userRepository.save(new User("admin", "Admin"));
    }
}