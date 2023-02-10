package com.michaelj;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.michaelj.dao.RegionDao;
import com.michaelj.domain.Region;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MyPokemonApplicationTests {
	@Autowired
	private RegionDao regionDao;

	@Test
	public void testGetAll() {
		List<Region> regions = regionDao.getAll();
		System.out.println(regions);
	}

}
