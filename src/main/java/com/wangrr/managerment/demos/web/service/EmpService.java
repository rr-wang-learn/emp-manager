package com.wangrr.managerment.demos.web.service;

import com.wangrr.managerment.demos.web.pojo.Emp;
import com.wangrr.managerment.demos.web.pojo.PageBean;

import java.time.LocalDate;


public interface EmpService {
    /*
       //查询数据总数
       public Long total();
       //查询数据封装到集合
       public List rows(Integer page,Integer pageSize);
       */
    //查询所有数据
    public PageBean lists(Integer page, Integer pageSize);

    //根据条件分页查询
    public PageBean find(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //根据条件删除数据
    public void delete(int[] ids);

    //根据数据新增员工
    public void add(Emp emp);

    //根据id查询数据
    public Emp list(Integer id);

    //根据数据修改员工
    public void alter(Emp emp);

    //根据数据验证登录
    public Emp login(Emp emp);
}
