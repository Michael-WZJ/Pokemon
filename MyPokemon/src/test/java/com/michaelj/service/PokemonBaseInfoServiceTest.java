package com.michaelj.service;

import com.michaelj.domain.PokemonBaseInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PokemonBaseInfoServiceTest {
    @Autowired
    private PokemonBaseInfoService baseInfoService;

    // 创建一个测试宝可梦
    private PokemonBaseInfo createTestPokemon(String code) {
        PokemonBaseInfo pokemon = new PokemonBaseInfo();
        pokemon.setCode(code);
        pokemon.setName("test");
        pokemon.setGen("test");
        pokemon.setNameEng("test");
        pokemon.setNameJpn("test");
        return pokemon;
    }

    @Test
    public void testGetAll() {
        List<PokemonBaseInfo> baseInfos = baseInfoService.getAll();
        System.out.println(baseInfos);
    }

    @Test
    public void testGetByCode() {
        assertTrue(baseInfoService.save(createTestPokemon("testGet")));
        PokemonBaseInfo pokemon = baseInfoService.getByCode("testGet");
        assertEquals(pokemon.getName(), "test");
        assertEquals(pokemon.getNameJpn(), "test");
        assertEquals(pokemon.getNameEng(), "test");
        assertTrue(baseInfoService.deleteByCode("testGet"));
    }

    @Test
    public void testSaveAndDeleteByCode() {
        assertTrue(baseInfoService.save(createTestPokemon("testSave")));
        assertTrue(baseInfoService.deleteByCode("testSave"));
    }

    @Test
    public void testUpdate() {
        assertTrue(baseInfoService.save(createTestPokemon("testUpdate")));
        PokemonBaseInfo pokemon = baseInfoService.getByCode("testUpdate");
        assertEquals(pokemon.getName(), "test");

        pokemon.setName("testUpdate");
        assertTrue(baseInfoService.update(pokemon));
        pokemon = baseInfoService.getByCode("testUpdate");
        assertEquals(pokemon.getName(), "testUpdate");
        assertTrue(baseInfoService.deleteByCode("testUpdate"));
    }
}
