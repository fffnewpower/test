package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController//里面封装了@Controller和@ResponseBody（可以将返回的结果转换为josn格式然后在发送给网页）
public class DeptContraller {
    //接受请求，响应数据
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping//规定只能响应get请求
    public Result findAll(){

        List<Dept> list = deptService.findAll();
        log.info("部门表数据："+list.toString());
        return Result.success(list);
    }

    //删除部门
//    //方案一：通过原始hettpsevletRequest对象获取请求参数
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest  request){
//        String i = request.getParameter("id");
//        int id = Integer.parseInt(i);
//        System.out.println( "删除的部门id号为"+id);
//        return Result.success();
//    }

//    //方案二：通过注解@RequestParam来获取请求参数，如果参数不存在，则会报错，传递空参数需要修改required属性为false
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required = false) Integer id){
//        System.out.println( "删除的部门id号为"+id);
//        return Result.success();
//    }

    //方案三：通过注解@RequestParam来获取请求参数，如果参数与方法参数一致，可以省略@RequestParam注解
    @DeleteMapping
    public Result delete(Integer id){
//        System.out.println( "删除的部门id号为"+id);
        log.info("删除的部门id号为:{}",id);
        deptService.deleteById(id);
        return Result.success();

    }

    //新增部门
    @PostMapping
    public Result insert(@RequestBody Dept dept){
//        System.out.println("新添加的部门为"+dept.getName());
        log.info("新添加的部门为"+dept.getName());
        deptService.insertByName(dept);
        return Result.success();
    }

    //修改部门
    //1.查询回显
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
//        System.out.println("接受的id为"+id);
        log.info("接收的id为:{}",id);
        return Result.success(deptService.findById(id));
    }

    //2.修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("需修改的部门为："+dept.getId()+" "+dept.getName());
        log.info("需修改的部门为：{} {}",dept.getId(),dept.getName());
        deptService.update(dept);
        return Result.success();
    }


}
