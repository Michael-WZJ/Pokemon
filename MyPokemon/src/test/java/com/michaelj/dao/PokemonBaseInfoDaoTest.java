package com.michaelj.dao;

import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
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
        PokeBaseInfoQuery query = new PokeBaseInfoQuery();
        query.setPokeBaseCode("test");
        //query.setName("蛙");
        //query.setGen("OP");
        //query.setProp1("毒");
        query.setProp2("草");
        //query.setNameEng("test");
        //query.setNameJpn("test");

        List<PokemonBaseInfo> baseInfos = baseInfoDao.getByCondition(query);
        System.out.println(baseInfos);


        query.setProp1("毒");
        System.out.println(baseInfoDao.getByCondition(query));

        query.setProp1(null);
        query.setProp2(null);
        System.out.println(baseInfoDao.getByCondition(query));
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
