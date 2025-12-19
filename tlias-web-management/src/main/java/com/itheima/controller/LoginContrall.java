package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginContrall {

    @Autowired
    private EmpService empService;

    /*
    * 登录接口
    * */
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("登录用户名为{}，用户密码为{}",emp.getUsername(),emp.getPassword());
        LoginInfo loginInfo   = empService.getEmpInfoByUsernameAndPassword(emp);

        if(loginInfo==null){
            return Result.error("用户名或密码错误");
        }

        return Result.success(loginInfo);

    }
}
