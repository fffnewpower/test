package com.itheima.mapper;

import com.itheima.pojo.ClazzOption;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {

    /*
     * 搜索员工职位及数量
     * */
    List<Map<String, Object>> getEmpJobData();


    List<Map> getEmpGenderData();

    List<Map> getStudentDegreeData();


    List<Map<String,Object>> getClazzCountData();
}
