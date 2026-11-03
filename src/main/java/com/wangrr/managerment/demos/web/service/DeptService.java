package com.wangrr.managerment.demos.web.service;


import com.wangrr.managerment.demos.web.pojo.Dept;

import java.util.List;

public interface DeptService {
    //  查询所有数据
    List<Dept> list();

    //   根据id删除数据
    void delete(Integer id);

    //  添加部门
    void add(Dept dept);

    //  根据id查询数据
    Dept select(Integer id);

    //  根据id修改数据
    void update(Integer id, String name);
}
