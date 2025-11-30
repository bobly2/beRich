package com.example.rich;

import com.example.rich.entity.u.KlineUEntity;
import com.example.rich.enums.TableNameEnum;
import com.example.rich.service.GetDataService;
import com.example.rich.utils.ComputeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class strategy {
    @Autowired
    private GetDataService getDataService;
    @Autowired
    private ComputeUtils computeUtils;

    //盈亏比1比2  如何判断上涨趋势？
    //如何实现一个n形态
    @Test
    public void huicai2() throws ParseException {
        String tableName = TableNameEnum.m5.getTableName();
        String symbol = "ZECUSDT";
        List<KlineUEntity> list5m = getDataService.getAllByTime(tableName, symbol, null, null);
        double all = 0;
        double su = 0;
        Map<String, BigDecimal> map = new HashMap();

        Boolean flagHad = false;
        BigDecimal benjin = new BigDecimal("100");
        for (int i = 6; i < list5m.size() - 11; i++) {
            int ups = 0;
            KlineUEntity en6 = list5m.get(i - 6);
            KlineUEntity en5 = list5m.get(i - 5);
            KlineUEntity en4 = list5m.get(i - 4);
            KlineUEntity en3 = list5m.get(i - 3);
            KlineUEntity en2 = list5m.get(i - 2);
            KlineUEntity en1 = list5m.get(i - 1);
            KlineUEntity en = list5m.get(i);
            String per = computeUtils.getPercent(en.getClosePrice(), en6.getClosePrice());
            boolean bool = false;
            if(flagHad==false){
                if (computeUtils.percentCompare(per, "3%")) {
                    //5%涨幅
                    bool = true;
                    System.out.println("波动超过3%::" + per+"," +computeUtils.dateToString(en.getCloseTime()));
                }
//            ups(en6, ups);
//            ups(en5, ups);
//            ups(en4, ups);
//            ups(en3, ups);
//            ups(en2, ups);
//            ups(en1, ups);
//            ups(en, ups);
                if (bool) {
                    //空  第一根k线ema20>ema60,第二根k线ema60大于ema20，是一个双均线的死叉
                    BigDecimal tr = en.getTr();
                    BigDecimal zhisun = en.getClosePrice().add(tr);
                    BigDecimal zhiying = en.getClosePrice().subtract(tr).subtract(tr);
                    map.put("zhisun", zhisun);
                    map.put("init", en.getClosePrice());
                    map.put("zhiying", zhiying);
                    flagHad = true;
                    all++;
                    continue;
                }
            }


            if (flagHad) {
                BigDecimal zhisun = map.get("zhisun");
                BigDecimal zhiying = map.get("zhiying");
                BigDecimal init = map.get("init");
                //到达止盈点
                if (computeUtils.getFlagDaYu(zhiying, en.getClosePrice())) {
                    String per1 = computeUtils.getPercent(en.getClosePrice(), init).replace("-", "");
                    benjin = computeUtils.countPercent(per1, benjin);
                    flagHad = false;
                    su++;
                    bool = false;
                    System.out.println("止盈:" + benjin+","+computeUtils.dateToString(en.getCloseTime()));
                }
                //到达止损点
                if (computeUtils.getFlagDaYu(en.getClosePrice(), zhisun)) {
                    String per1 = "-" + computeUtils.getPercent(en.getClosePrice(), init);
                    benjin = computeUtils.countPercent(per1, benjin);
                    flagHad = false;
                    bool = false;
                    System.out.println("止损:" + benjin+","+computeUtils.dateToString(en.getCloseTime()));
                }
            }

        }

        double ss = su / all;
        System.out.

                println(symbol + ":总数:" + all + ",成功次数:" + su + "，胜率:" + ss + ",最后本金:" + benjin);
//        System.out.println("超短线均线回踩：" + computeUtils.dateToString(en.getOpenTime())
//                + ",ema20:" + en.getEma20() + ",ema60:" + en.getEma60() + ",买入：" + en.getClosePrice()
//                + ",卖出:" + en2.getClosePrice()
//                + ",5k涨幅:" + percent + ",benjin:" + benjin);
    }

    public void ups(KlineUEntity en, int ups) {
        if (!en.getPercent().contains("-")) {
            ups++;
        }
    }
}
