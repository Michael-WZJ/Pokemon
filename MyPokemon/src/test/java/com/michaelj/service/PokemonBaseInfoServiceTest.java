package com.michaelj.service;

import com.michaelj.domain.converter.PokemonBaseInfoConverter;
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

    @Autowired
    private PokemonBaseInfoConverter baseInfoConverter;

    // 创建一个测试宝可梦
    private PokemonBaseInfo createTestPokemon(String code, String name) {
        PokemonBaseInfo pokemon = new PokemonBaseInfo();
        pokemon.setPokeBaseCode(code);
        pokemon.setPokeBaseName(name);
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
        String code = "testGet";
        String name = "testGet";
        baseInfoService.deleteByCode(code);

        assertTrue(baseInfoService.save(baseInfoConverter.toDto(createTestPokemon(code, name))));
        PokemonBaseInfoDTO pokemon = baseInfoService.getByCode("testGet");
        assertEquals(pokemon.getPokeBaseName(), "testGet");
        assertEquals(pokemon.getNameJpn(), "test");
        assertEquals(pokemon.getNameEng(), "test");
        assertTrue(baseInfoService.deleteByCode(code));

        baseInfoService.deleteByCode(code);
    }

    @Test
    public void testSaveAndDeleteByCode() {
        String code = "testSave";
        String name = "testSave";
        baseInfoService.deleteByCode(code);

        assertTrue(baseInfoService.save(baseInfoConverter.toDto(createTestPokemon(code, name))));
        assertTrue(baseInfoService.deleteByCode(code));
    }

    @Test
    public void testUpdate() {
        String code = "testUpdate";
        String name = "testUpdate";
        baseInfoService.deleteByCode(code);

        assertTrue(baseInfoService.save(baseInfoConverter.toDto(createTestPokemon(code, name))));
        PokemonBaseInfoDTO pokemon = baseInfoService.getByCode(code);
        assertEquals(pokemon.getPokeBaseName(), "testUpdate");

        pokemon.setPokeBaseName("testUpdateNew");
        assertTrue(baseInfoService.update(pokemon));
        pokemon = baseInfoService.getByCode(code);
        assertEquals(pokemon.getPokeBaseName(), "testUpdateNew");
        assertTrue(baseInfoService.deleteByCode(code));
    }
}
