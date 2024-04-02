package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Dto.UserDto;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }

    @PostMapping("/register")
    public String registerSave(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return "User registered successfully";
    }

    // You can define similar methods for login and other operations if needed
}
