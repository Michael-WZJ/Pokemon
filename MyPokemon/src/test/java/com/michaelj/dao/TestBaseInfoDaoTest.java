package com.michaelj.dao;

import com.michaelj.domain.PokemonBaseInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestBaseInfoDaoTest {
    @Autowired
    private TestDao testDao;

    @Test
    public void testGetAll() {
        List<PokemonBaseInfo> baseInfos = testDao.getAll();
        System.out.println(baseInfos);
    }
}
