package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /*
    * 查询所有员工的信息以及所在的部门名称
    * */
//    @Select("select e.*,d.name as deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc ")
//    public List<Emp> List();

    public List<Emp> list(EmpQueryParam empQueryParam);

   // @Options(useGeneratedKeys = true , keyProperty = "id")//主键返回：获取到数据库返回的主键
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfoById(Integer id);

    void update(Emp emp);

    @Select("select * from emp")
    List<Emp> getEmpList();

    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp getEmpInfoByUsernameAndPassword(Emp emp);


//
    /*
    * 原始分页查询实现方法
    * */
//    @Select("select count(*) " +
//            "from emp e left join dept d " +
//            "on e.dept_id = d.id")
//    Long getTotal();
//
//    @Select("select e.*,d.name as deptName from emp e " +
//            "left join dept d on e.dept_id = d.id " +
//            "order by update_time " +
//            "limit #{start},#{pageSize}")
//    List<Emp> getRows(Integer start , Integer pageSize);



}
