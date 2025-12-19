package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpContraller {
    @Autowired
    private EmpService empService;

    /*
    * 分页查询员工信息
    * */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        PageResult<Emp> pageResult =empService.page(empQueryParam);
        return Result.success(pageResult);
    }


    //添加员工信息
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("传递进来的数据为"+emp);
        empService.insert(emp);
        return Result.success();
    }

/*
*删除员工信息以及对应的动作表
* */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("需要删除的员工id为：{}",ids.toString());

        empService.deleteByIds(ids);

        return Result.success();
    }

    /*
    * 修改员工信息页面的显示回显
    * */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("需要修改的员工id为：{}",id);
        Emp emp = empService.getInfoById(id);
        return Result.success(emp);
    }

    /*
    * 修改员工信息
    * */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改后员工的信息为{}",emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getEmpList(){
        log.info("调用list代码");
        List<Emp> list = empService.getEmpList();
        log.info("员工列表为{}",list);
        return Result.success(list);
    }
}
