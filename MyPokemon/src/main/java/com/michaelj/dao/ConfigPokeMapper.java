package com.michaelj.dao;

import com.michaelj.domain.entity.ConfigPoke;
import com.michaelj.domain.query.ConfigPokeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfigPokeMapper {
    List<ConfigPoke> selectForList(@Param("req") ConfigPokeQuery query);

    int selectForCount(@Param("req") ConfigPokeQuery query);


    int save(ConfigPoke config);

    /**
     * 修改
     */
    int update(ConfigPoke config);


    int deleteById(Long id, String updatedAt);

}
