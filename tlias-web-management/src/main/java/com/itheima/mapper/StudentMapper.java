package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper {


    List<Student> getStudentInfo(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student getStudentInfoById(Integer id);

    void update(Student student);

    @Update("update student set violation_score =#{score},violation_count=#{count} where id = #{id}")
    void handleViolation(Integer id, Short score,Short count);
}
