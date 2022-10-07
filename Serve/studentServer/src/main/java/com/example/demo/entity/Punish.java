package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("punish")
@Data
public class Punish {
    @TableId(type = IdType.AUTO)
    private String id;
    private String studentId;
    private String levels;
    private String effect;
    private String recTime;
    private String description;
}
