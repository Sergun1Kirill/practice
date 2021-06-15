package com.epam.practice.practice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("main","Главная страница погодки");
        return "home";
    }

}
