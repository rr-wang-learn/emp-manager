package com.wangrr.managerment.demos.web.controller;

import com.wangrr.managerment.demos.web.pojo.Dept;
import com.wangrr.managerment.demos.web.pojo.Result;
import com.wangrr.managerment.demos.web.service.impl.DeptServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    //    查询部门数据
    @Autowired
    private DeptServiceImpl deptService;

    @GetMapping
    public Result list() {
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //  删除部门数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    //    添加部门数据
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("根据数据添加部门{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //    根据id查询数据
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id) {
        log.info("根据id查询部门");
        Dept select = deptService.select(id);
        return Result.success(select);
    }
//  根据id修改数据
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept.getId(), dept.getName());
        log.info("根据id修改数据");
        return Result.success();
    }
}
