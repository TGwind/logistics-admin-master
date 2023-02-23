package com.example.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.logistics.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name=#{name} and password=#{password} and permission=#{permission}")
    User login(String name, String password,Integer permission);
}
