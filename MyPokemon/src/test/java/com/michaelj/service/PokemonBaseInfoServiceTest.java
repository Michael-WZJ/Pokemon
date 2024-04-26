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

    @Test
    public void testGenNext() {
        String code = "0999";
        String codeDash = "0999-04";

        System.out.println(baseInfoService.generateNextCode(code, false));
        System.out.println(baseInfoService.generateNextCode(code, true));
        System.out.println(baseInfoService.generateNextCode(codeDash, false));
        System.out.println(baseInfoService.generateNextCode(codeDash, true));

        assertEquals(baseInfoService.generateNextCode("0001", false), "0002");
        assertEquals(baseInfoService.generateNextCode("0001", true), "0001-01");
        assertEquals(baseInfoService.generateNextCode("0099-01", false), "0100");
        assertEquals(baseInfoService.generateNextCode("0099-01", true), "0099-02");
    }
}
