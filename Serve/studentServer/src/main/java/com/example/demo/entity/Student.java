package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("student")
@Data  //自动生成getter、setter
public class Student {
    @TableId(type = IdType.AUTO) //设置主键、自动生成
    private Integer id;
    private String name;
    private String sex;
    private String dept;
    private String lass;
    private Date birth;
    private String nativePlace;

}
