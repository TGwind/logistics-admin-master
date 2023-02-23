package com.example.logistics.controller;

import com.example.logistics.common.Result;
import com.example.logistics.dao.TransportMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TransportController {

    @Resource
    TransportMapper transportMapper;

    @GetMapping("/transport/{pid}")
    public Result<?> getBypid(@PathVariable String pid){
        return Result.success(transportMapper.selectBypid(pid));

    }
}
