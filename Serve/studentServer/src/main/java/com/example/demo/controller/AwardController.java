package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Award;
import com.example.demo.mapper.AwardMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController //定义返回的是json数据的Controller
@RequestMapping("/award") //定义一个路由
public class AwardController {
    @Resource
    AwardMapper awardMapper;

    @PostMapping
    public Result<?> save(@RequestBody Award award) {//把json对象转化为java对象
        awardMapper.insert(award);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody Award award) {//把json对象转化为java对象
        awardMapper.updateById(award);
        return Result.success();
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        awardMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping("/show")
    public Result<?> show() {
        List<Award> awardList = awardMapper.selectList(null);
        return Result.success(awardList);
    }

    @GetMapping("/{id}")
    public Result<?> showById(@PathVariable Integer id) {
        return Result.success(awardMapper.selectById(id));
    }


}
