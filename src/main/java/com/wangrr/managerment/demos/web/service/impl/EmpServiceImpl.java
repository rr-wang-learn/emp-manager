package com.wangrr.managerment.demos.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangrr.managerment.demos.web.mapper.EmpMapper;
import com.wangrr.managerment.demos.web.pojo.Emp;
import com.wangrr.managerment.demos.web.pojo.PageBean;
import com.wangrr.managerment.demos.web.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class EmpServiceImpl implements EmpService {
    @SuppressWarnings("SpringInjectionAutowiringInspection")
    @Autowired
    private EmpMapper empMapper;

    /*
        @Override
        public Long total() {
            return empMapper.total();
        }

        @Override
        public List rows(Integer page, Integer pageSize) {
            return empMapper.rows((page - 1) * pageSize, pageSize);
        }
    */
    //分页查询所有数据
    @Override
    public PageBean lists(Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行查询
        Page<Emp> p = (Page<Emp>) empMapper.lists();
        //获取pageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    //根据条件查询数据
    @Override
    public PageBean find(Integer page, Integer pageSize, String name,
                         Short gender, LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        //执行查询
        Page<Emp> p = (Page<Emp>) empMapper.find(name, gender, begin, end);
        //获取pageBean对象
        return new PageBean(p.getTotal(), p.getResult());
    }

    //根据条件查询所有数据
    @Override
    public void delete(int[] ids) {
        for (int id : ids) {
            empMapper.delete(id);
        }
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp list(Integer id) {
        return empMapper.list(id);
    }

    @Override
    public void alter(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.alter(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.GetByUsernameAndPassword(emp);
    }

}
