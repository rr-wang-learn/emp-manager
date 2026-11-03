package com.wangrr.managerment.demos.web.filter;


import com.alibaba.fastjson.JSONObject;
import com.wangrr.managerment.demos.web.pojo.Result;
import com.wangrr.managerment.demos.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //获取请求的URI
        String requestURI = req.getRequestURI();
        log.info("获取请求的URI,{}", requestURI);

        //判断是否是登录请求，是就放行
        if (requestURI.contains("login")) {
            log.info("登录请求，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //获取请求头中的令牌
        String token = req.getHeader("token");
        //判断令牌是否为空，是就返回NO_LOGIN
        if (!StringUtils.hasLength(token)) {
            log.info("令牌为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }
        //如果令牌不为空，解析令牌，出现异常返回NO_LOGIN
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析异常，jwt令牌失效或被篡改");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return;
        }
        //令牌解析成功，放行
        log.info("令牌解析成功");
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
