package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /*
    * 查询班级信息
    * */
    List<Clazz> getClazzInfo(ClazzQueryParam clazzQueryParam);

    /*
    *根据id删除班级信息
    * */
    @Delete("delete from clazz where id =#{id}")
    void deleteClazzInfoByID(Integer id);

    void insert(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz getClazzInfoById(Integer id);

    @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject} where id=#{id}")
    void update(Clazz clazz);

    @Select("select * from clazz")
    List<Clazz> getClazzList();
}
