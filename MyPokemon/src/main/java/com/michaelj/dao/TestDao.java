package com.michaelj.dao;

import com.michaelj.domain.PokemonBaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

//TODO 添加@Mapper
@Mapper
public interface TestDao {
    @Select("select * from TestBaseInfo")
    List<PokemonBaseInfo> getAll();
}
