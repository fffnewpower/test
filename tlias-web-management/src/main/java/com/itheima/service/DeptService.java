package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {
    public List<Dept> findAll();

    void deleteById(Integer id);

    void insertByName(Dept dept);

    Dept findById(Integer id);

     void update(Dept dept);
}
