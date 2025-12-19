package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentContraller {

    @Autowired
    private StudentService studentService;

    /*
    * 查询学生信息
    * */
    @GetMapping
    public Result getStudentInfo(StudentQueryParam studentQueryParam){
        log.info("查询所有学生信息");
        PageResult<Student> list = studentService.getStudentInfo(studentQueryParam);
        return Result.success(list);
    }

    /*
    * 删除学生
    * */

    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable List<Integer> ids){
        log.info("需要删除的ids为{},类型为{}",ids,ids.getClass().getName());
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /*
    * 添加学生
    * */
    @PostMapping
    public Result insert(@RequestBody Student student){
        log.info("添加的学生信息为:{}",student);
        studentService.insert(student);
        return Result.success();
    }

    /*
    * 修改时的显示回显，根据id显示学生信息
    * */
    @GetMapping("/{id}")
    public Result getStudentInfoById(@PathVariable Integer id){
        log.info("根据id查询学生信息");
        Student student = studentService.getStudentInfoById(id);
        return Result.success(student);
    }

    /*
    * 修改学员信息
    * */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改的学员信息为:{}",student);
        studentService.update(student);
        return Result.success();
    }

    /*
    * 违纪处理
    * */

    @PutMapping("/violation/{id}/{score}")
    public Result handleViolation(@PathVariable Integer id,@PathVariable Short score ){
        log.info("违纪处理学生id为:{}，违纪扣分为:{}",id,score);
        studentService.handleViolation(id,score);
        return Result.success();
    }
}
