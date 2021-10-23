package com.joysun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author joysun
 * @create 2021-10-22 17:31
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.joysun.feign"})
@MapperScan(basePackages = {"com.joysun.dao"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
