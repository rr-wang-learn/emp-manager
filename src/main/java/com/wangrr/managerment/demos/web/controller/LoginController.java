package com.wangrr.managerment.demos.web.controller;

import com.wangrr.managerment.demos.web.pojo.Emp;
import com.wangrr.managerment.demos.web.pojo.Result;
import com.wangrr.managerment.demos.web.service.EmpService;
import com.wangrr.managerment.demos.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        Emp e = empService.login(emp);
        log.info("员工登录:{}", e);
        //如果成功登录，下发JWT令牌
        if(e != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }
        //登录失败
        return Result.error("用户名或密码错误");
    }
}