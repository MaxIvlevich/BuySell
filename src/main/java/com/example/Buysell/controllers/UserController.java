package com.example.Buysell.controllers;

import com.example.Buysell.models.User;
import com.example.Buysell.repositories.UserRepository;
import com.example.Buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/registration")
    public String registration(){
        return "registration";

    }
    @GetMapping("/login")
    public String login(){
        return "login";

    }
    @PostMapping ("/registration")
    public String createUser( User user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";

    }
}
