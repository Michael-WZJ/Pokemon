package com.michaelj.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pages")
public class PageController {
    @GetMapping("/home")
    public String getHomePage() {
        return "My Pokemon";
    }
}
