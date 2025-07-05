package com.diaryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userId", "user123");
        return "index";
    }
}