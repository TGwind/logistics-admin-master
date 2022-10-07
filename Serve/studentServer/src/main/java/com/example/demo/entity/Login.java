package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("login")
@Data
public class Login {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String userPassword;
}
