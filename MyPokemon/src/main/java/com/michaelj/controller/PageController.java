package com.michaelj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需要 放行 静态资源访问
 * 建议使用配置类放行
 * 目前未放行
 */
@Controller
@RequestMapping("/pages")
public class PageController {
    @GetMapping("/home")
    public String getHomePage() {
        return "index.html";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test.html";
    }
}
