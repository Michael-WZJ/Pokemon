package com.michaelj.dao;

import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

//TODO_wzj 添加@Mapper
@Mapper
public interface PokemonBaseInfoDao {
    List<PokemonBaseInfo> getByCondition(PokeBaseInfoQuery query);

    /**
     * 条件查询宝可梦信息
     * @param query
     * @return
     */
    List<PokemonBaseInfo> selectPageList(PokeBaseInfoQuery query);

    /**
     * 条件查询宝可梦信息，只获取数据量
     * @param query
     * @return
     */
    long selectPageListCount(PokeBaseInfoQuery query);

    /**
     * 根据 名称 查询宝可梦
     * @param name
     * @return
     */
    PokemonBaseInfo selectByName(String name);

    /**
     * 根据 编号前缀 查询 宝可梦数量
     * @param codePrefix
     * @return
     */
    int selectByCodePrefix(String codePrefix);

    /**
     * 根据 编号列表 查询宝可梦
     * @param codeList
     * @return
     */
    List<PokemonBaseInfo> selectByCodeList(List<String> codeList);

    @Select("select * from PokeBaseInfo")
    List<PokemonBaseInfo> getAll();

    @Select("select * from PokeBaseInfo where poke_base_code = #{code} and deleted = 0")
    PokemonBaseInfo getByCode(String code);

    @Select("select count(*) from PokeBaseInfo where deleted = 0")
    Long getPokeCount();

//    @Insert("insert into PokeBaseInfo (poke_base_code, poke_base_name, gen, prop1, prop2, name_jpn, name_eng, poke_base_pic) " +
//            "values(#{pokeBaseCode}, #{pokeBaseName}, #{gen}, #{prop1}, #{prop2}, #{nameJpn}, #{nameEng}, #{pokeBasePic})")
    int save(PokemonBaseInfo pokemon);

    /**
     * 修改宝可梦基本信息
     * @param pokemon
     * @return
     */
    int update(PokemonBaseInfo pokemon);

    /**
     * 删除宝可梦基本信息
     * @param code 
     * @return
     */
    int deleteByCode(String code);

    /**
     * 批量删除宝可梦基本信息
     * @param codeList
     * @return
     */
    int deleteByCodeList(@Param("list") List<String> codeList);
}
