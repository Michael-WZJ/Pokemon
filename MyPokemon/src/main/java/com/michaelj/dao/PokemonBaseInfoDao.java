package com.michaelj.dao;

import com.michaelj.domain.PokemonBaseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO 添加@Mapper
@Mapper
public interface PokemonBaseInfoDao {
    @Select("select * from PokeBaseInfo")
    List<PokemonBaseInfo> getAll();

    @Select("select * from PokeBaseInfo where code = #{code}")
    PokemonBaseInfo getByCode(String code);

    @Insert("insert into PokeBaseInfo (code, name, gen, prop1, prop2, name_jpn, name_eng, pic) " +
            "values(#{code}, #{name}, #{gen}, #{prop1}, #{prop2}, #{nameJpn}, #{nameEng}, #{pic})")
    int save(PokemonBaseInfo pokemon);

    @Delete("delete from PokeBaseInfo where code = #{code}")
    int delete(String code);

    @Update("update PokeBaseInfo set name = #{name}, gen = #{gen}, prop1 = #{prop1}, prop2 = #{prop2}, " +
            "name_jpn = #{nameJpn}, name_eng = #{nameEng}, pic = #{pic} where code = #{code}")
    int update(PokemonBaseInfo pokemon);
}
