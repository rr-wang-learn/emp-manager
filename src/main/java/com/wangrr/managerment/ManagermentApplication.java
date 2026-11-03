package com.wangrr.managerment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ManagermentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagermentApplication.class, args);
    }

}
