package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController //定义返回的是json数据的Controller
@RequestMapping("/student") //定义一个路由
public class StudentController {
    @Resource
    StudentMapper studentMapper;
    @PostMapping
    public Result<?> save(@RequestBody Student student){//把json对象转化为java对象
        studentMapper.insert(student);
        return  Result.success();
    }
    @PutMapping("/update")
    public Result<?> update(@RequestBody Student student){//把json对象转化为java对象
        studentMapper.updateById(student);
        return  Result.success();
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Integer> ids) {
        studentMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @GetMapping("/show")
    public Result<?> show(){
        List<Student> studentList = studentMapper.selectList(null);
        return Result.success(studentList);
    }
    @GetMapping("/{id}")
    public Result<?> showById(@PathVariable Integer id){
        return Result.success(studentMapper.selectById(id));
    }
}
