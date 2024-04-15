package com.michaelj.dao;

import com.michaelj.domain.entity.PokemonBaseInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PokemonBaseInfoDaoTest {
    @Autowired
    private PokemonBaseInfoDao baseInfoDao;

    @Test
    public void testGetAll() {
        List<PokemonBaseInfo> baseInfos = baseInfoDao.getAll();
        System.out.println(baseInfos);
    }

    @Test
    public void testGetByCondition() {
        PokemonBaseInfo pokemon = new PokemonBaseInfo();
        pokemon.setPokeBaseCode("test");
        //pokemon.setName("蛙");
        //pokemon.setGen("OP");
        //pokemon.setProp1("毒");
        pokemon.setProp2("草");
        //pokemon.setNameEng("test");
        //pokemon.setNameJpn("test");

        List<PokemonBaseInfo> baseInfos = baseInfoDao.getByCondition(pokemon);
        System.out.println(baseInfos);


        pokemon.setProp1("毒");
        System.out.println(baseInfoDao.getByCondition(pokemon));

        pokemon.setProp1(null);
        pokemon.setProp2(null);
        System.out.println(baseInfoDao.getByCondition(pokemon));
    }

    @Test
    public void testUpdate() {
        PokemonBaseInfo pokemon = new PokemonBaseInfo();
        pokemon.setPokeBaseCode("testUpdate");
        pokemon.setPokeBaseName("蛙");
        pokemon.setGen("OP");
        baseInfoDao.save(pokemon);

        pokemon.setPokeBaseName("青蛙");
        baseInfoDao.update(pokemon);
        System.out.println(baseInfoDao.getByCode("testUpdate"));

        baseInfoDao.deleteByCode("testUpdate");
    }
}
