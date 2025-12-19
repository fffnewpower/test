package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportContraller {
    @Autowired
    private ReportService reportService;

    /*
    * 显示员工的职称及数量
    * */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        JobOption jobOption =reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        return Result.success(reportService.getEmpGenderData());
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        return Result.success(reportService.getStudentDegreeData());
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        return Result.success(reportService.getClazzCountData());
    }


}
