package com.example.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.logistics.entity.Package;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface PackageMapper extends BaseMapper<Package> {
    @Select("select * from package where sender=#{sender} order by send_date desc") //按日期降序
    List<Package> sendOut(String sender);

    @Select("select * from package where receiver=#{receiver} order by send_date desc")
    List<Package> receive(String receiver);

    @Select("select * from package where id=#{pid} and sender=#{sender} order by send_date desc")
    List<Package> findSendOut(Integer pid, String sender);

    @Select("select * from package where id=#{pid} and receiver=#{receiver} order by send_date desc")
    List<Package> findReceive(Integer pid, String receiver);

    @Insert("UPDATE package SET receive_date=#{date} WHERE id=#{pid} and receiver=#{receiver}  ")
    int checkGoods(Date date, Integer pid, String receiver);

}
