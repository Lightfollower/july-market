package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.findByPhone(principal.getName()).get();
        System.out.println(user);
        model.addAttribute("user", user);
        return "profile";
    }
}
