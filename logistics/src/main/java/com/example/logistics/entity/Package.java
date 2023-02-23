package com.example.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("package")
public class Package {
    private String id;
    private String sender;
    private String senderNumber;
    private String sendAddress;
    private Date sendDate;
    private String receiver;
    private String receiverNumber;
    private String receiveAddress;
    private Date receiveDate;
    private  String content;
    private double weight;
    private String logistics;
    private String note;

}
