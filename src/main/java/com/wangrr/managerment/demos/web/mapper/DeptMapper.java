package com.wangrr.managerment.demos.web.mapper;

import com.wangrr.managerment.demos.web.pojo.Dept;
import com.wangrr.managerment.demos.web.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    public List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    public void delete(Integer id);

    @Insert("insert into dept(name, create_time, update_time)  values (#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);

    @Select("select * from dept where id = #{id}")
    public Dept select(Integer id);

    @Update("update dept set name = #{name} where id = #{id}")
    public void update(@Param("id") Integer id, @Param("name") String name);

}
