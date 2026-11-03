package com.wangrr.managerment.demos.web.mapper;

import com.wangrr.managerment.demos.web.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /*
    @Select("select count(*) from emp")
    Long total();

    @Select("select * from emp limit #{page},#{pageSize}")
    List<Emp> rows(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
*/
    //查询所有员工
    @Select("select * from emp")
    List<Emp> lists();

    //根据id查询员工
    @Select("select * from emp where id = #{id}")
    Emp list(Integer id);

    //根据条件查找员工
    List<Emp> find(@Param("name") String name, @Param("gender") Short gender,
                   @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    //根据id删除员工
    @Delete("delete from emp where id = #{id}")
    void delete(Integer id);

    //根据部门id删除员工
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
    //根据数据新增员工
    void add(Emp emp);

    //根据数据修改员工
    void alter(Emp emp);

    //根据用户名密码验证登录
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp GetByUsernameAndPassword(Emp emp);
}
