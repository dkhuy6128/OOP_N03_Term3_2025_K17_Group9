package com.diaryapp.service;

import com.diaryapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean addUser(User user);
    List<User> getAllUsers();
    boolean deleteUser(String id);
    Optional<User> findById(String id);
    User getById(String id);
    void init();
}
