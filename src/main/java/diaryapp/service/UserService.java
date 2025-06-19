package main.java.diaryapp.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Map<String, String> users = new HashMap<>();

    public void addUser(String userId, String name) {
        users.put(userId, name);
    }

    public boolean userExists(String userId) {
        return users.containsKey(userId);
    }
}
