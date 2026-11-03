package com.wangrr.managerment.demos.web.controller;

import com.wangrr.managerment.demos.web.pojo.Emp;
import com.wangrr.managerment.demos.web.pojo.PageBean;
import com.wangrr.managerment.demos.web.pojo.Result;
import com.wangrr.managerment.demos.web.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpServiceImpl empService;

    /*
        @GetMapping
        public Result page(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
            //获取pageBean对象
            PageBean pageBean = empService.list(page, pageSize);
            log.info("分页式查询员工数据:{},{}",page,pageSize);
            return Result.success(pageBean);
        }
    */
    //根据条件分页式查询员工数据
    @GetMapping
    public Result find(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        PageBean pageBean = empService.find(page, pageSize, name, gender, begin, end);
        log.info("根据条件分页式查询员工数据{},{},{},{}", name, gender, begin, end);
        return Result.success(pageBean);
    }

    //根据路径传输id查找数据
    @GetMapping("/{id}")
    public Result list(@PathVariable Integer id) {
        log.info("根据路径传输id查找数据");
        Emp e = empService.list(id);
        return Result.success(e);
    }

    //根据条件删除数据
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable int[] ids) {
        log.info("根据条件删除数据");
        empService.delete(ids);
        return Result.success();
    }

    //根据数据新增员工
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("根据数据新增员工");
        empService.add(emp);
        return Result.success();
    }

    //根据数据修改员工信息
    @PutMapping
    public Result alter(@RequestBody Emp emp){
        log.info("根据数据修改员工");
        empService.alter(emp);
        return Result.success();
    }
}
