package com.example.rich;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rich.entity.BeiLiEntityDto;
import com.example.rich.entity.u.KlineUEntity;
import com.example.rich.enums.TableNameEnum;
import com.example.rich.service.GetDataService;
import com.example.rich.utils.ComputeUtils;
import com.example.rich.utils.SignUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
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
    @Autowired
    private SignUtils signUtils;

    @Test
    public void strategys() throws ParseException {
        //mtu2();
        String symbol = "ZECUSDT";
        beiliNew(symbol);
    }
    //盈亏比1比2  如何判断上涨趋势？
    //如何实现一个n形态

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
            if (flagHad == false) {
                if (computeUtils.percentCompare(per, "3%")) {
                    //5%涨幅
                    bool = true;
                    System.out.println("波动超过3%::" + per + "," + computeUtils.dateToString(en.getCloseTime()));
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
                    System.out.println("止盈:" + benjin + "," + computeUtils.dateToString(en.getCloseTime()));
                }
                //到达止损点
                if (computeUtils.getFlagDaYu(en.getClosePrice(), zhisun)) {
                    String per1 = "-" + computeUtils.getPercent(en.getClosePrice(), init);
                    benjin = computeUtils.countPercent(per1, benjin);
                    flagHad = false;
                    bool = false;
                    System.out.println("止损:" + benjin + "," + computeUtils.dateToString(en.getCloseTime()));
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


    public void bao() {
        String tableName = TableNameEnum.m5.getTableName();
        String symbol = "ZECUSDT";
        List<KlineUEntity> list5m = getDataService.getAllByTime(tableName, symbol, null, null);
        for (int i = 6; i < list5m.size() - 11; i++) {


        }
    }

    //340天
    public void mtu2() throws ParseException {
        String tableName = TableNameEnum.m5.getTableName();
        String symbol = "ZECUSDT";
        List<KlineUEntity> list5m = getDataService.getAllByTime(tableName, symbol, null, null);

        double all = 0;
        double su = 0;
        BigDecimal benjin = new BigDecimal("100");

        for (int i = 5; i < list5m.size() - 6; i++) {
            KlineUEntity enf5 = list5m.get(i - 5);
            KlineUEntity enf1 = list5m.get(i - 1);
            KlineUEntity en0 = list5m.get(i);
            KlineUEntity enz1 = list5m.get(i + 1);
            KlineUEntity enz5 = list5m.get(i + 5);
            String dd = computeUtils.getPercent(enf5.getClosePrice(), en0.getClosePrice());
            BigDecimal d = computeUtils.chufa(en0.getVolume(), enf1.getVolume());
            BigDecimal d2 = new BigDecimal("2.5");
            if (
                    computeUtils.getFlagDaYu(d, d2)
                            && computeUtils.percentCompare(dd, "2%")) {

                all++;
                //视为下跌反弹
                String percent = computeUtils.getPercent(enz5.getClosePrice(), enz1.getClosePrice());

                if (!percent.contains("-")) {
                    su++;
                }
                benjin = computeUtils.countPercent(percent, benjin);
//                System.out.println("成交量异常：" + computeUtils.dateToString(en0.getOpenTime())
//                        + ",5k涨幅:" + percent+",benjin:"+benjin);
                i = i + 5;
            }

        }
        double ss = su / all;
        System.out.println(symbol + ":总数:" + all + ",成功次数:" + su + "，胜率:" + ss + ",收益:" + benjin);
    }

    public void beiliNew(String symbol) throws ParseException {
        String tableName = TableNameEnum.m5.getTableName();
        List<KlineUEntity> list5m = getDataService.getAllByTime(tableName, symbol, null, null);

        HashMap<Long, String> map = new HashMap<Long, String>();
        double all = 0;
        double su = 0;
        boolean flagHave =false;
        BigDecimal buy = new BigDecimal("0");
        BigDecimal sell = new BigDecimal("0");
        BigDecimal gap = new BigDecimal("0");

        BigDecimal benjin = new BigDecimal("100");
        for (int i = 24; i < list5m.size() - 30; i++) {
            if (flagHave) {
                if(computeUtils.getFlagDaYu(list5m.get( i).getClosePrice(),buy)){
                    //止盈
                    su++;
                    String percent = computeUtils.getPercent(list5m.get( i).getClosePrice(), buy);
                    String per = percent;
                    benjin = computeUtils.countPercent(per, benjin);
                    flagHave = false;
                    System.out.println("止盈:"+list5m.get( i).getClosePrice()+","+computeUtils.dateToString(list5m.get( i).getCloseTime()));
                }
                if(computeUtils.getFlagDaYu(sell,list5m.get( i).getClosePrice())){
                    //止

                    String percent = computeUtils.getPercent(list5m.get( i).getClosePrice(), buy);
                    benjin = computeUtils.countPercent(percent, benjin);
                    flagHave = false;
                    System.out.println("止损:"+list5m.get( i).getClosePrice()+","+computeUtils.dateToString(list5m.get( i).getCloseTime()));
                }


            }else{
                //5*24 = 120 2h
                List<KlineUEntity> list = list5m.subList(i - 24, i);
                BeiLiEntityDto dto = signUtils.flagBeiliNew(list);
                if (dto!=null) {
                    buy = dto.getStartEn().getClosePrice();
                    gap = dto.getHighPrice().subtract(dto.getLowPrice()).multiply(new BigDecimal("2"));
                    sell = buy.subtract(gap);
                    flagHave = true;
                    all++;
                }

            }
        }
        double ss = su / all;
        System.out.println(symbol + ",all:" + all + ",成功:" + su + ",胜率:" + ss + ",benjin:" + benjin);
    }

    public void beili(String symbol) throws ParseException {
        String tableName = TableNameEnum.m5.getTableName();
        List<KlineUEntity> list5m = getDataService.getAllByTime(tableName, symbol, null, null);

        HashMap<Long, String> map = new HashMap<Long, String>();
        double all = 0;
        double su = 0;
        BigDecimal benjin = new BigDecimal("100");
        for (int i = 24; i < list5m.size() - 30; i++) {
            //5*24 = 120 2h
            List<KlineUEntity> list = list5m.subList(i - 24, i);
            BeiLiEntityDto dto = signUtils.flagBeili2(list);
            if (StringUtils.isNotBlank(dto.getType())) {
                if (map.containsKey(Long.valueOf(dto.getStartDate().getTime()))) {
                    continue;
                } else {
                    map.put(Long.valueOf(dto.getStartDate().getTime()), null);
                }
                all++;
                String percent = computeUtils.getPercent(list5m.get(i + 6).getClosePrice(), list5m.get(i + 1).getClosePrice());
                if (percent.contains("-")) {
                    su++;
                    String per = percent.replace("-", "");
                    benjin = computeUtils.countPercent(per, benjin);
                } else {
                    String per = "-" + percent;
                    benjin = computeUtils.countPercent(per, benjin);
                }
                System.out.println("出现背离,入场：" + computeUtils.dateToString(list5m.get(i).getOpenTime())
                        + "离场:" + computeUtils.dateToString(list5m.get(i + 6).getOpenTime())
                        + ",10k涨幅:" + percent + ",本金:" + benjin);
                i = i + 7;
            }
        }
        double ss = su / all;
        System.out.println(symbol + ",all:" + all + ",成功:" + su + ",胜率:" + ss + ",benjin:" + benjin);

    }
}
