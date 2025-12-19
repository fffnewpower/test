package com.itheima.service;

import com.itheima.pojo.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfoById(Integer id);

    void update(Emp emp);


    List<Emp> getEmpList();

    LoginInfo getEmpInfoByUsernameAndPassword(Emp emp);
}
