package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> getStudentInfo(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    Student getStudentInfoById(Integer id);

    void update(Student student);

    void handleViolation(Integer id, Short score);
}
