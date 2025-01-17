package com.example.Buysell.controllers;

import com.example.Buysell.cofigyrations.UserConfig;
import com.example.Buysell.models.MyUser;
import com.example.Buysell.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@AllArgsConstructor
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
    public String createUser(MyUser user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";

    }
}
