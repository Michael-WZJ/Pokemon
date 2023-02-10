package com.michaelj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypoke")
public class MyPokeController {
    @GetMapping("/主页")
    public String getHomePage() {
        return "My Pokemon";
    }
}
