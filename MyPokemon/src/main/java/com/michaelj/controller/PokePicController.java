package com.michaelj.controller;

import com.michaelj.application.PokePicApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/poke/picture")
public class PokePicController {
    @Autowired
    private PokePicApplicationService picApplicationService;


    /**
     * 按code查询
     */
    @GetMapping("/{pokeCode}")
    public ResponseEntity<Resource> getBasePicByCode(@PathVariable String pokeCode) {
        ResponseEntity<Resource> response = picApplicationService.getBasePicByCode(pokeCode);
        return response;
    }


}
