package com.example.rich.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {
    @Bean
    public ExecutorService getPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("bi-an-%d").build();
        ExecutorService threadPool = new ThreadPoolExecutor(10,
                20,
                200L,
                TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(2048),namedThreadFactory);
        return threadPool;
    }
}
