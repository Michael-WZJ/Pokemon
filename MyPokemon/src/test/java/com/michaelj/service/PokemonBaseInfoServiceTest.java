package com.michaelj.service;

import com.michaelj.domain.PokemonBaseInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PokemonBaseInfoServiceTest {
    @Autowired
    private PokemonBaseInfoService baseInfoService;

    @Test
    public void testGetAll() {
        List<PokemonBaseInfo> baseInfos = baseInfoService.getAll();
        System.out.println(baseInfos);
    }
}
