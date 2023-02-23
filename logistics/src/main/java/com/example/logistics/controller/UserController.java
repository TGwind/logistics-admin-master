package com.example.logistics.controller;


import com.example.logistics.common.Result;
import com.example.logistics.dao.UserMapper;
import com.example.logistics.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {

    @Resource
    UserMapper userMapper;

    //查询所有记录
    @GetMapping("/users/queryAll")
    public List<User> queryAll() {
        return userMapper.selectList(null); //查询数据表中所有数据
    }

    @PostMapping("/login")   //用户登录
    public Result<?> userLogin(@RequestBody User user){
        User user1 = userMapper.login(user.getName(),user.getPassword(), user.getPermission());
        if(user1 ==null){
            return Result.error("-1","数据为空");
        }else {
            return Result.success(user1);
        }
    }

    @RequestMapping("/requestParam")
    public String requestParam(@RequestParam String name, @RequestParam String age, @RequestParam(required = false,defaultValue = "没有额外信息吖") String extra){
        return "欢迎您："+age+"岁的"+name+" 这是你携带的额外信息："+extra;
    }


}
