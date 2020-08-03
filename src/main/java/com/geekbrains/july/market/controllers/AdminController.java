package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminka")
    public String adminka(Model model){
        model.addAttribute("users", userService.findAll());
        return "adminka";
    }

    @GetMapping("/delete/{id}")
    public String ololo(@PathVariable int id){
        userService.deleteById(id);
        System.out.println(id);
        return "redirect:/adminka";
    }
}
