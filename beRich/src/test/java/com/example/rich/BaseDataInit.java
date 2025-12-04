package com.example.rich;


import com.example.rich.enums.TableNameEnum;
import com.example.rich.mapper.u.BaseUMapper;
import com.example.rich.service.BaseDataService;
import com.example.rich.service.GetDataService;
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


    @Autowired
    private GetDataService getDataService;


    @Test
    public void initData() throws ParseException, InterruptedException {
        for (int i = 0; i < 1; i++) {
            String symbol = "ZECUSDT";
            baseDataService.initKlineUList(symbol);
            System.out.println("进度:"+i);
            Thread.sleep(200);
        }
    }


    @Test
    public void updateData() throws ParseException, InterruptedException {
        for (int i = 0; i < 30; i++) {
//            String symbol = "ZECUSDT";
//            baseDataService.updateKlineUList(symbol);
//            System.out.println("进度:"+i);
//            Thread.sleep(100);
        }
    }
    @Test
    public void lixiang() throws ParseException {
        String tableName = TableNameEnum.m5.getTableName();
        String symbol = "ZECUSDT";
        baseDataService.updateMACD(symbol,tableName);
        //List<KlineUEntity> list =  baseUMapper.getBySymbol(tableName,symbol,null,null);
//        for (int i = 0; i < list.size(); i++){
//            KlineUEntity en = list.get(i);
//            System.out.println("" +
//                    "");
//        }
    }
}
