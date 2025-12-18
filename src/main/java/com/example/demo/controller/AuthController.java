package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            return "Email already registered";
        }

        userService.saveUser(user);
        return "User registered successfully";
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser == null) {
            return "Invalid email";
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }
}
