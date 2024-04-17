package com.michaelj.service;

import com.michaelj.domain.dto.PokemonBaseInfoDTO;
import com.michaelj.domain.entity.PokemonBaseInfo;
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
        pokemon.setPokeBaseCode(code);
        pokemon.setPokeBaseName("test");
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
        PokemonBaseInfoDTO pokemon = baseInfoService.getByCode("testGet");
        assertEquals(pokemon.getPokeBaseName(), "test");
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
        PokemonBaseInfoDTO pokemon = baseInfoService.getByCode("testUpdate");
        assertEquals(pokemon.getPokeBaseName(), "test");

        pokemon.setPokeBaseName("testUpdate");
        assertTrue(baseInfoService.update(pokemon));
        pokemon = baseInfoService.getByCode("testUpdate");
        assertEquals(pokemon.getPokeBaseName(), "testUpdate");
        assertTrue(baseInfoService.deleteByCode("testUpdate"));
    }
}
