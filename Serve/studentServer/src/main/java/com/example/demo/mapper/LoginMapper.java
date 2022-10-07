package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Login;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;

public interface LoginMapper extends BaseMapper<Login> {
    // 查询用户名
    @Select("select user_password from login where user_name=#{user_name}")
    Login selectByName(String username);
}
