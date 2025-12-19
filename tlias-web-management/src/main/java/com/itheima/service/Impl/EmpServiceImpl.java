package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogMapper empLogMapper;

    /*
    * 原始分页查询实现方法
    * */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //计算总记录数
//        Long total = empMapper.getTotal();
//        //获得数据列表
//        Integer start  = (page-1)*pageSize;
//        List<Emp> rows = empMapper.getRows(start,pageSize);
//
//        return new PageResult<Emp>(total, rows);
//    }


    /*
    * 使用PageHelper实现分页查询
    * */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.启动PageHelper
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行普通的查询语句,PageHelper会拦截查询语句并将其改造为分页查询，输出Page<>对象
        //注意：1.PageHellper只会对其后的第一条Sql语句进行改造
        //     2.被改造的sql语句不能加";"号
        List<Emp> empList = empMapper.list(empQueryParam);

        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//默认只有runtimeException异常才会回滚,rollbackfor可以控制能触发回滚的异常
    @Override
    public void insert(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr> empExprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(empExprList)) {
                empExprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insert(empExprList);
            }
        }finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工："+emp.toString());
            empLogMapper.insert(empLog);
        }
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        empMapper.deleteByIds(ids);

        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfoById(Integer id) {
        return empMapper.getInfoById(id);
    }

    @Transactional
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        //1.修改emp表中的信息
        empMapper.update(emp);
        //2.修改emo_expr表中的信息
        //先删除原有信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //再写入新信息
        //判断是否为空集合
        List<EmpExpr> exprList =emp.getExprList();
        if(!exprList.isEmpty()) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insert(exprList);
        }
    }

    @Override
    public List<Emp> getEmpList() {
        return empMapper.getEmpList();
    }

    @Override
    public LoginInfo getEmpInfoByUsernameAndPassword(Emp emp) {
        Emp empResult= empMapper.getEmpInfoByUsernameAndPassword(emp);
        if(empResult==null){
            return null;
        }

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",empResult.getId());
        claims.put("username",empResult.getUsername());
        String jwt = JwtUtil.createJwt(claims);
        LoginInfo loginuser = new LoginInfo(empResult.getId(),empResult.getUsername(),empResult.getName(), jwt);

        return loginuser;
    }


}
