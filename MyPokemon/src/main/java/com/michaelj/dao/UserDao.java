package com.michaelj.dao;

import com.michaelj.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select id, username, \"group\" from User")
    List<User> getAll();

    @Select("select * from User where username = #{username} and password = #{password}")
    User getByCondition(User user);
}
