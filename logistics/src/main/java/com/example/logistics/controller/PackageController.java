package com.example.logistics.controller;


import com.example.logistics.common.Result;
import com.example.logistics.dao.PackageMapper;
import com.example.logistics.entity.Package;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PackageController {

    @Resource
    PackageMapper packageMapper;

    //查询所有记录
    @GetMapping("/package/queryAll")
    public Result<?> queryAll() {
          return Result.success(packageMapper.selectList(null)); //查询数据表中所有数据
    }

}
