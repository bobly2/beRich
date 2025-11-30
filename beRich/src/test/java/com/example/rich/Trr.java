package com.example.rich;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rich.entity.u.KlineUEntity;
import com.example.rich.enums.TableNameEnum;
import com.example.rich.mapper.u.BaseUMapper;
import com.example.rich.service.BaseDataService;
import com.example.rich.service.GetDataService;
import com.example.rich.service.impl.BaseDataServiceImpl;
import com.example.rich.utils.ComputeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@SpringBootTest
public class Trr {
    @Autowired
    private GetDataService getDataService;
    @Autowired
    private ComputeUtils computeUtils;
    @Test
    public void huicai2( ) throws ParseException {
        String tableName = TableNameEnum.m5.getTableName();
        String symbol = "ZECUSDT";

        List<KlineUEntity> list5m = getDataService.getAllByTime(tableName,symbol,null,null);
        double all = 0;
        double su = 0;
        BigDecimal benjin = new BigDecimal("100");
        for (int i = 2; i < list5m.size() - 11; i++) {
            KlineUEntity en0 = list5m.get(i - 1);
            KlineUEntity en = list5m.get(i);
            KlineUEntity en2 = list5m.get(i + 6);
            //空  第一根k线ema20>ema60,第二根k线ema60大于ema20，是一个双均线的死叉
            if (computeUtils.getFlagDaYu(en.getEma60(), en.getEma20())) {
                if (computeUtils.getFlagDaYu(en0.getEma20(), en0.getEma60())
                ) {
                    String percent = computeUtils.getPercent(en2.getClosePrice(), en.getClosePrice());
                    all++;
                    String per = "";
                    if (percent.contains("-")) {
                        su++;
                        per = percent.replace("-", "");
                    } else {
                        per = "-" + percent;

                    }
                    benjin = computeUtils.countPercent(per, benjin);
                    System.out.println("超短线均线回踩：" + computeUtils.dateToString(en.getOpenTime())
                            + ",ema20:" + en.getEma20() + ",ema60:" + en.getEma60() + ",买入：" + en.getClosePrice()
                            + ",卖出:" + en2.getClosePrice()
                            + ",5k涨幅:" + percent + ",benjin:" + benjin);
                    i = i + 6;
                }

            }

        }
        double ss = su / all;
        System.out.println(symbol + ":总数:" + all + ",成功次数:" + su + "，胜率:" + ss + ",收益:" + benjin);
    }





}
