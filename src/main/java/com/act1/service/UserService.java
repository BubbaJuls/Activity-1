package com.act1.service;

import com.act1.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    public String register(User user) {
        if (userList.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
            return "Username already existing";
        }
        user.setId(String.valueOf(userList.size() + 1));
        userList.add(user);
        return "Registration successful";
    }

    public User login(String username, String password) {
        return userList.stream()
            .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
            .findFirst()
            .orElse(null);
    }
}
