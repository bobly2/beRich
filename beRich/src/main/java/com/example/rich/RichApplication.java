package com.example.rich;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.rich.mapper.u")  // 扫描BaseUMapper所在的包
public class RichApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(RichApplication.class, args);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
