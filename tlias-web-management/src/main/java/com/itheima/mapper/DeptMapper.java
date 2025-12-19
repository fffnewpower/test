package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //默认情况下，驼峰命名规则，不会自动映射，会导致SQL中_连接的字段名不会映射到对象属性中
//方法一:手动映射
//    @Results(
//    {
//        @Result(column = "create_time",property = "createTime"),
//        @Result(column = "update_time",property = "updateTime")
//    })
    //方法二:在sql语句中起别名
    //方法三：打开mybatis全局配置文件，将自动映射打开
    @Select("select id,name,create_time,update_time from dept")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insertByName(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept findById(Integer id);

    @Update("update dept set name =#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
