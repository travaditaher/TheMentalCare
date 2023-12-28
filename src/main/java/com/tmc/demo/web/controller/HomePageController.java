package com.tmc.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping(value = {"/", "/home"})
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/about")
    public String aboutPage() {
        return "about";
    }
    
}