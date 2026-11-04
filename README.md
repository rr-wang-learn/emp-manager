```
# 员工信息管理系统
基于SpringBoot+MyBatis的后端接口项目，实现员工、部门信息的增删改查、模糊条件查询并分页展示

## 技术栈
- Java 1.8
- SpringBoot
- MyBatis
- MySQL 5.7
- JWT

## 项目功能
- 员工登录，JWT令牌身份认证
- 员工、部门信息新增、删除、修改
- 多条件模糊查询 + 分页展示
- 拦截器统一校验登录状态

## 启动步骤
```bash
git clone https://github.com/rr-wang-learn/emp-manager.git
```

1. IDEA 打开项目，等待 Maven 依赖加载完成
2. 修改 `application.yml` 中的数据库连接账号密码
3. 在 MySQL 中执行 `sql/init.sql` 脚本，初始化数据表
4. 运行项目启动类
5. 使用 Postman 调试接口

## 项目亮点

- 使用 MyBatis XML 编写动态 SQL，适配多条件查询
- 自定义拦截器，基于 JWT 实现无状态登录校验
- 分页工具封装，复用性强
- 分层开发，代码结构清晰

## 项目总结

巩固 SpringBoot 整合 MyBatis 开发流程，熟悉后端接口开发、数据库设计、Postman 接口调试。