package com.michaelj.dao;

import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO 添加@Mapper
@Mapper
public interface PokemonBaseInfoDao {
    /**
     * 条件查询宝可梦信息
     * @param query
     * @return
     */
    List<PokemonBaseInfo> selectPageList(PokeBaseInfoQuery query);

    @Select("select * from PokeBaseInfo")
    List<PokemonBaseInfo> getAll();

    @Select("select * from PokeBaseInfo where poke_base_code = #{code}")
    PokemonBaseInfo getByCode(String code);

    List<PokemonBaseInfo> getByCondition(PokemonBaseInfo pokemon);

    @Select("select count(*) from PokeBaseInfo where deleted = 0")
    Long getPokeCount();

    @Insert("insert into PokeBaseInfo (poke_base_code, poke_base_name, gen, prop1, prop2, name_jpn, name_eng, poke_base_pic) " +
            "values(#{pokeBaseCode}, #{pokeBaseName}, #{gen}, #{prop1}, #{prop2}, #{nameJpn}, #{nameEng}, #{pokeBasePic})")
    int save(PokemonBaseInfo pokemon);

    int update(PokemonBaseInfo pokemon);

    @Delete("delete from PokeBaseInfo where poke_base_code = #{code}")
    int deleteByCode(String code);
}
