package com.michaelj.dao;

import com.michaelj.domain.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

//TODO 添加@Mapper
@Mapper
public interface RegionDao {
    @Select("select * from Region")
    List<Region> getAll();
}
