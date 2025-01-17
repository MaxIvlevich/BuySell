package com.example.Buysell.controllers;

import com.example.Buysell.models.User;
import com.example.Buysell.repositories.UserRepository;
import com.example.Buysell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/registration")
    public String createUser(User user, Model model){
        if(userService.createUser(user)){
            model.addAttribute("ErrorMessage","Пользователь с email: "+user.getEmail() +"уже существует ");
            return "/registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";

    }
}
