package com.itheima.service.Impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public JobOption getEmpJobData() {
        JobOption jobOption=new JobOption();
        List<Map<String,Object>> empJobDataList = reportMapper.getEmpJobData();
        jobOption.setJobList(
                empJobDataList.stream().map(
                        empJobDataMap-> empJobDataMap.get("pos")
                ).toList()
        );
        jobOption.setDataList(empJobDataList.stream().map(
                empJobDataMap-> empJobDataMap.get("num")
        ).collect(Collectors.toList()));

        return jobOption;


    }

    @Override
    public List<Map> getEmpGenderData() {
        log.info("统计职工性别比例");
        return reportMapper.getEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        log.info("统计学员学历信息");
        return reportMapper.getStudentDegreeData();
    }

    @Override
    public ClazzOption getClazzCountData() {
        List<Map<String,Object>> clazzCountDataList = reportMapper.getClazzCountData();
        ClazzOption clazzOption = new ClazzOption();
        clazzOption.setClazzList(
                clazzCountDataList.stream().map(
                        clazzCountDataMap-> clazzCountDataMap.get("name")
                ).toList()
        );
        clazzOption.setDataList(
                clazzCountDataList.stream().map(
                        clazzCountDataMap-> clazzCountDataMap.get("value")
                ).toList()
        );
        return clazzOption;
    }
}
