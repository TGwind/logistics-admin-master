package com.example.logistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("package")
public class Package {
    @TableId(type = IdType.AUTO) //设置主键、自动生成
    private Integer id;
    private String sender;
    private String senderNumber;
    private String sendAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sendDate;
    private String receiver;
    private String receiverNumber;
    private String receiveAddress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveDate;
    private  String content;
    private double weight;
    private String logistics;
    private String note;

}
