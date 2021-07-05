package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.entities.dtos.SystemUser;
import com.geekbrains.july.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String showMyLoginPage(Model model) {
        model.addAttribute("systemUser", new SystemUser());
        return "registration-form";
    }

    @PostMapping("/register/process")
    public String processRegistrationForm(@ModelAttribute("systemUser") @Validated SystemUser systemUser, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        Optional<User> existing = userService.findByPhone(systemUser.getPhone());
        if (existing.isPresent()) {
            model.addAttribute("registrationError", "User with phone number: [" + systemUser.getPhone() + "] is already exist");
            systemUser.setPhone(null);
            model.addAttribute("systemUser", systemUser);
            return "registration-form";
        }
        userService.save(systemUser);
        return "registration-confirmation";
    }
}
