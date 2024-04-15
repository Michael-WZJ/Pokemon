package com.michaelj.service;

import com.michaelj.domain.base.Page;
import com.michaelj.domain.entity.PokemonBaseInfo;
import com.michaelj.domain.query.PokeBaseInfoQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// TODO 开启事务【涉及一个方法包含多个ddl操作，才需要开启事务】
//@Transactional
public interface PokemonBaseInfoService {
    /**
     * 条件查询宝可梦信息
     * @param query
     * @return
     */
    Page<PokemonBaseInfo> selectPageList(PokeBaseInfoQuery query);

    /**
     * 查找全部宝可梦基础信息
     * @return
     */
    List<PokemonBaseInfo> getAll();

    /**
     * 按code查询
     * @param code
     * @return
     */
    PokemonBaseInfo getByCode(String code);

    /**
     * 条件查询
     * @param pokemon
     * @return
     */
    List<PokemonBaseInfo> getByCondition(PokemonBaseInfo pokemon);



    /**
     * 查询全部宝可梦的数量
     * @return
     */
    Long getPokeCount();

    /**
     * 保存
     * @param pokemon
     * @return
     */
    boolean save(PokemonBaseInfo pokemon);

    /**
     * 修改
     * @param pokemon
     * @return
     */
    boolean update(PokemonBaseInfo pokemon);

    /**
     * 按code删除
     * @param code
     * @return
     */
    boolean deleteByCode(String code);
}
