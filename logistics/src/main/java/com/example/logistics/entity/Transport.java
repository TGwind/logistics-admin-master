package com.example.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("transport")
@Data
public class Transport {
    private String id;
    private String pid;
    private Date date;
    private String progress;
    private String note;

}
