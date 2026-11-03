package com.wangrr.managerment.demos.web.mapper;

import com.wangrr.managerment.demos.web.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface DeptLogMapper {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Insert("insert into dept_log (create_time, description) values (#{createTime},#{description})")
    public void insert(DeptLog deptLog);
}
