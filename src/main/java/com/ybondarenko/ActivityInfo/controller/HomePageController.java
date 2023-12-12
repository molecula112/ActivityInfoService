package com.ybondarenko.ActivityInfo.controller;

import com.ybondarenko.ActivityInfo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }
}
