package com.example.rich;


import com.example.rich.mapper.u.BaseUMapper;
import com.example.rich.service.BaseDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;


@SpringBootTest
public class BaseDataInit {
    @Autowired
    private BaseUMapper baseUMapper;
    @Autowired
    private BaseDataService baseDataService;

    @Test
    public void initData() throws ParseException, InterruptedException {
        for (int i = 0; i < 30; i++) {
            String symbol = "ZECUSDT";
            baseDataService.initKlineUList(symbol);
            System.out.println("进度:"+i);
            Thread.sleep(200);
        }
    }


    @Test
    public void updateData() throws ParseException, InterruptedException {
        for (int i = 0; i < 30; i++) {
            String symbol = "ZECUSDT";
            baseDataService.updateKlineUList(symbol);
            System.out.println("进度:"+i);
            Thread.sleep(100);
        }
    }
}
