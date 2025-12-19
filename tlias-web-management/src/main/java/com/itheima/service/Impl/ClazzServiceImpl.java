package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> getClazzInfo(ClazzQueryParam clazzQueryParam) {
       //使用pageHelper自动分页
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList= clazzMapper.getClazzInfo(clazzQueryParam);
        log.info("查询结果为：{}",clazzList);
        clazzList.forEach(clazz->{
            if(LocalDate.now().isAfter(clazz.getBeginDate())&&LocalDate.now().isBefore(clazz.getEndDate())){
                clazz.setStatus("已开班");
            }else if(LocalDate.now().isAfter(clazz.getEndDate())){
                clazz.setStatus("已结课");
            }else{
                clazz.setStatus("未开班");
            }
        });
        Page<Clazz> clazzPage = (Page<Clazz>) clazzList;
        return  new PageResult<>(clazzPage.getTotal(),clazzPage.getResult());
    }

    /*
    * 根据id删除班级信息
    * */
    @Override
    public void deleteClazzInfoByID(Integer id) {
        clazzMapper.deleteClazzInfoByID(id);
    }


    /*
    * 添加班级信息
    * */
    @Override
    public void insert(Clazz clazz) {
        //添加创建时间和更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getClazzInfoById(Integer id) {
        Clazz clazz = clazzMapper.getClazzInfoById(id);
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> getClazzList() {
        return clazzMapper.getClazzList();
    }


}
