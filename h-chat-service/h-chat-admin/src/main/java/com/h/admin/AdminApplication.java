package com.h.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * admin启动类
 * @author: domainDrivenDesign
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.h.admin.mapper")
@EnableAspectJAutoProxy //开启注解aop
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
