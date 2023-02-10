package com.michaelj.dao;

import com.michaelj.domain.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface RegionDao {
    @Select("select * from Region")
    List<Region> getAll();
}
