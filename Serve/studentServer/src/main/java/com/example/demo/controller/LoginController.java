package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.entity.Login;
import com.example.demo.entity.User;
import com.example.demo.mapper.LoginMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
    @Resource
    LoginMapper loginMapper;

    @PostMapping()
    public Result<?> login(@RequestBody Login loginParam) {
        Login loginPassword = loginMapper.selectByName(loginParam.getUserName());  //根据用户名查询用户的密码
        QueryWrapper<Login> queryWrapper = new QueryWrapper<>(); //新建一个查询
        queryWrapper.eq("user_Name", loginParam.getUserName()); //
        queryWrapper.eq("user_Password", loginPassword.getUserPassword());
        Login res = loginMapper.selectOne(queryWrapper);
        if(res==null){
            return Result.error("0","mmm");
        }
        return Result.success(res);
    }
}
