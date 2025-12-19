package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> getClazzInfo(ClazzQueryParam clazzQueryParam);

    void deleteClazzInfoByID(Integer id);

    void insert(Clazz clazz);

    Clazz getClazzInfoById(Integer id);

    void update(Clazz clazz);


    List<Clazz> getClazzList();
}
