package com.penzjg.iomp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.penzjg.iomp.project.system.mapper")
public class IompApplication {

    public static void main(String[] args) {
        SpringApplication.run(IompApplication.class, args);
    }

}
