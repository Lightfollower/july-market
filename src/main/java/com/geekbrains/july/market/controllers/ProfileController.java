package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(Principal principal, Model model) {
        if (principal != null) {
            User user = userService.findByPhone(principal.getName()).get();
            model.addAttribute("user", user);
        }
        return "profile";
    }

    @PostMapping("/change_email")
    public String changeMail(@ModelAttribute User user) {
        System.out.println(user);
        userService.saveOrUpdate(user);
        return "redirect:/profile";
    }
}
