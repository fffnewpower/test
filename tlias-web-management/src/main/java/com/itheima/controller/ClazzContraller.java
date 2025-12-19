package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clazzs")
public class ClazzContraller {

    @Autowired
    private ClazzService clazzService;



    /*
    * 班级列表查询
    * */
    @GetMapping
    public Result getClazzInfo(ClazzQueryParam clazzQueryParam){
        log.info("课程筛选条件为{}",clazzQueryParam);
        PageResult<Clazz> list=  clazzService.getClazzInfo(clazzQueryParam);
        return Result.success(list);
    }

    /*
    *根据id删除班级
    * */
    @DeleteMapping("/{id}")
    public Result deleteClazzInfoByID(@PathVariable Integer id){
        log.info("传入的班级id为：{}",id);
        clazzService.deleteClazzInfoByID(id);
        return Result.success();
    }

    /*
    * 添加班级
    * */
    @PostMapping
    public Result insert(@RequestBody Clazz clazz){
        log.info("添加的班级信息为:{}",clazz);
        clazzService.insert(clazz);
        return Result.success();
    }


    /*
    * 查询所有员工（显示班主任id）
    * */
    @GetMapping("/list")
    public Result getEmpList(){
        List<Clazz> list = clazzService.getClazzList();
        log.info("结果为{}",list);
        return Result.success(list);
    }

    /*
    * 根据id查询班级信息(查询回显)
    * */
    @GetMapping("/{id}")
    public Result getClazzInfoById(@PathVariable Integer id){
        log.info("查询的班级id为:{}",id);
        Clazz clazz = clazzService.getClazzInfoById(id);
        return Result.success(clazz);
    }

    /*
    * 修改班级信息
    * */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改的班级信息为:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
