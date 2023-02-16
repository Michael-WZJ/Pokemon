package com.michaelj.controller;

import com.michaelj.domain.PokemonBaseInfo;
import com.michaelj.service.PokemonBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mypoke/api")
public class MyPokeController {
    @Autowired
    private PokemonBaseInfoService baseInfoService;

    @GetMapping("/主页")
    public String getHomePage() {
        return "My Pokemon";
    }

    @GetMapping("/baseinfos")
    public List<PokemonBaseInfo> getAllBaseInfo() {
        //System.out.println("controller get");
        return baseInfoService.getAll();
    }
}
