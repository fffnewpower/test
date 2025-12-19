package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult<Student> getStudentInfo(StudentQueryParam studentQueryParam) {
        log.info("查询所有学生信息");
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> students= studentMapper.getStudentInfo(studentQueryParam);
        Page<Student> page = (Page<Student>) students;
        log.info("查询结果为{}",page);
        return new PageResult<Student>(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void insert(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getStudentInfoById(Integer id) {

        return studentMapper.getStudentInfoById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    @Transactional
    public void handleViolation(Integer id, Short score) {
        Student student = studentMapper.getStudentInfoById(id);
        student.setViolationScore((short) (student.getViolationScore()+score));
        student.setViolationCount((short) (student.getViolationCount()+1));
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.handleViolation(id,student.getViolationScore(),student.getViolationCount());
    }
}
