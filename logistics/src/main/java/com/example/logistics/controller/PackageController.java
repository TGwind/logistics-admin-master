package com.example.logistics.controller;


import com.example.logistics.common.Result;
import com.example.logistics.dao.PackageMapper;
import com.example.logistics.entity.Package;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class PackageController {

    @Resource
    PackageMapper packageMapper;

    //查询所有快递记录
    @GetMapping("/package/queryAll")
    public Result<?> queryAll() {
        return Result.success(packageMapper.selectList(null)); //查询数据表中所有数据
    }

    //查询用户的寄快递记录
    @GetMapping("/package/sendOut/{sender}")
    public Result<?> sendOut(@PathVariable String sender) {
        return Result.success(packageMapper.sendOut(sender)); //查询数据表中数据
    }

    //查询用户的收快递记录
    @GetMapping("/package/receive/{receiver}")
    public Result<?> receive(@PathVariable String receiver) {
        return Result.success(packageMapper.receive(receiver)); //查询数据表中数据
    }

    //添加快递记录
    @PostMapping("/package/toSend")
    public Result<?> tosend(@RequestBody Package packageee) {
        return Result.success(packageMapper.insert(packageee)); //插入包裹数据
    }

    //按单号查询寄快递记录
    @GetMapping("/package/findSendOut/{pid}/{sender}")
    public Result<?> findSendOut(@PathVariable Integer pid,@PathVariable String sender) {
        return Result.success(packageMapper.findSendOut(pid,sender));
    }
    //按单号查询寄快递记录
    @GetMapping("/package/findReceive/{pid}/{receiver}")
    public Result<?> findReceive(@PathVariable Integer pid,@PathVariable String receiver) {
        return Result.success(packageMapper.findReceive(pid,receiver));
    }

    //收件人确认收货
    @PostMapping("/package/checkGoods")
    public Result<?> checkGoods(@RequestBody Package pack){ //使用RequestBody可以使前端传过来的对象转为java实体类，即使前端传过来的属性不全只有部分也能转换，很方便

        //java Date类型无法直接存入数据库类型为Datetime的，需转换
        Timestamp t = new Timestamp(pack.getReceiveDate().getTime());

        return Result.success(packageMapper.checkGoods(t, pack.getId(), pack.getReceiver()));
    }
}
