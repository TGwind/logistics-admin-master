package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Transfer;
import com.example.demo.mapper.TransferMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController //定义返回的是json数据的Controller
@RequestMapping("/transfer") //定义一个路由
public class TransferController {
    @Resource
    TransferMapper transferMapper;

    @PostMapping
    public Result<?> save(@RequestBody Transfer transfer) {//把json对象转化为java对象
        transferMapper.insert(transfer);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Transfer transfer) {//把json对象转化为java对象
        transferMapper.updateById(transfer);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        transferMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/show")
    public Result<?> show() {
        List<Transfer> transferList = transferMapper.selectList(null);
        return Result.success(transferList);
    }

    @GetMapping("/{id}")
    public Result<?> showById(@PathVariable Integer id) {
        return Result.success(transferMapper.selectById(id));
    }


}
