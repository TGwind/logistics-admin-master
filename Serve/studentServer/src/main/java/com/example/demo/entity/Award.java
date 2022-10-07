package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("award")
@Data
public class Award {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String studentId;
    private String levels;
    private String recTime;
    private String description;
}
