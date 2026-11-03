package com.wangrr.managerment.demos.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wangrr.managerment.demos.web.pojo.Result;
import com.wangrr.managerment.demos.web.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //获取请求的URI
        String requestURI = req.getRequestURI();
        log.info("获取请求的URI,{}", requestURI);

        //判断是否是登录请求，是就放行
        if (requestURI.contains("login")) {
            log.info("登录请求，放行...");
            return true;
        }

        //获取请求头中的令牌
        String token = req.getHeader("token");
        //判断令牌是否为空，是就返回NO_LOGIN
        if (!StringUtils.hasLength(token)) {
            log.info("令牌为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String jsonString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonString);
            return false;
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
            return false;
        }
        //令牌解析成功，放行
        log.info("令牌解析成功");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
