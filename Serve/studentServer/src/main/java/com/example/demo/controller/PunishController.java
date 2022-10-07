package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Punish;
import com.example.demo.mapper.PunishMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/punish")
public class PunishController {
    @Resource
    PunishMapper punishMapper;

    @PostMapping
    public Result<?> save(@RequestBody Punish punish) {//把json对象转化为java对象
        punishMapper.insert(punish);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Punish punish) {//把json对象转化为java对象
        punishMapper.updateById(punish);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        punishMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/show")
    public Result<?> show() {
        List<Punish> punishList = punishMapper.selectList(null);
        return Result.success(punishList);
    }

    @GetMapping("/{id}")
    public Result<?> showById(@PathVariable Integer id) {
        return Result.success(punishMapper.selectById(id));
    }

}
