package com.wangrr.managerment.demos.web.service.impl;

import com.wangrr.managerment.demos.web.mapper.DeptLogMapper;
import com.wangrr.managerment.demos.web.mapper.DeptMapper;
import com.wangrr.managerment.demos.web.mapper.EmpMapper;
import com.wangrr.managerment.demos.web.pojo.Dept;
import com.wangrr.managerment.demos.web.pojo.DeptLog;
import com.wangrr.managerment.demos.web.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Override
    public List<Dept> list() {
        List<Dept> list = deptMapper.list();
        return list;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        try {
            //根据id删除部门
            deptMapper.delete(id);
            //根据部门id删除员工
            empMapper.deleteByDeptId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogMapper.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept select(Integer id) {
        return deptMapper.select(id);
    }

    @Override
    public void update(Integer id, String name) {
        deptMapper.update(id, name);
    }
}
