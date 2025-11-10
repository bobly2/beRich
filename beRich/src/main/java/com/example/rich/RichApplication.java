package com.example.rich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RichApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(RichApplication.class, args);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
