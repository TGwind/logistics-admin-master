package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("transfer")
@Data
public class Transfer {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer studentId;
    private String transfer;
    private String recTime;
    private String description;

}
