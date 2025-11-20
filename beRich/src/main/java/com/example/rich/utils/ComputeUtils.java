package com.example.rich.utils;


import com.example.rich.dto.LimitQueue;
import com.example.rich.entity.xian.KlineXianEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/9 17:33
 * @Version: 1.0
 */
@Slf4j
@Component
public class ComputeUtils {


    //线程不安全
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式


    public String dateToString(Date date) {
        return sdf.format(date);
    }

    //通过已有均价，计算下一个均价
    public BigDecimal getNewAverage(BigDecimal average, BigDecimal firstPrice, BigDecimal lastPrice, int n) {
        BigDecimal d = lastPrice.subtract(firstPrice).divide(new BigDecimal(n), 4, BigDecimal.ROUND_HALF_UP);
        return average.add(d);
    }


    //计算n个数中的最小值
    public BigDecimal getLow(List<BigDecimal> list) {
        BigDecimal d = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (getFlagDaYu(d, list.get(i))) {
                d = list.get(i);
            }
        }
        return d;
    }

    //计算n个数中的最小值
    public BigDecimal getLowVol(LimitQueue<KlineXianEntity> limitQueue) {
        List<BigDecimal> list = new ArrayList<>();
        Queue<KlineXianEntity> queue = limitQueue.getQueue();
        for (KlineXianEntity en5m : queue) {
            list.add(en5m.getVolume());
        }
        BigDecimal dd = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (getFlagDaYu(dd, list.get(i))) {
                dd = list.get(i);
            }
        }
        return dd;
    }

    //获取tang 低
    public BigDecimal getTangLowPrice(LimitQueue<KlineXianEntity> limitQueue) {
        List<BigDecimal> list = new ArrayList<>();
        Queue<KlineXianEntity> queue = limitQueue.getQueue();
        for (KlineXianEntity en5m : queue) {
            list.add(en5m.getLowPrice());
        }
        BigDecimal dd = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (getFlagDaYu(dd, list.get(i))) {
                dd = list.get(i);
            }
        }
        return dd;
    }

    public BigDecimal getTangHighPrice(LimitQueue<KlineXianEntity> limitQueue) {
        List<BigDecimal> list = new ArrayList<>();
        Queue<KlineXianEntity> queue = limitQueue.getQueue();
        for (KlineXianEntity en5m : queue) {
            list.add(en5m.getHighPrice());
        }
        BigDecimal dd = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (getFlagDaYu(list.get(i), dd)) {
                dd = list.get(i);
            }
        }
        return dd;
    }

    //计算n个数中的最大值
    public BigDecimal getHigh(List<BigDecimal> list) {
        BigDecimal d = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (!getFlagDaYu(d, list.get(i))) {
                d = list.get(i);
            }
        }
        return d;
    }

    //计算n个数中的最大值 类
    public KlineXianEntity getHighEntity(List<KlineXianEntity> list) {
        BigDecimal d = list.get(0).getClosePrice();
        int is = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!getFlagDaYu(d, list.get(i).getClosePrice())) {
                d = list.get(i).getClosePrice();
                is = i;
            }
        }
        return list.get(is);
    }

    //计算n个数的平均值
    public BigDecimal getMA(List<BigDecimal> list, int n) {
        int h = list.size() - 1;
        BigDecimal s = new BigDecimal(n);
        BigDecimal d = new BigDecimal("0");
        while (n > 0 & h >= 0) {
            d = d.add(list.get(h));
            n--;
            h--;
        }
        d = d.divide(s, 4, BigDecimal.ROUND_HALF_UP);
        return d;
    }

    public Boolean percentCompare(String percent1, String percentCompare) throws ParseException {
        Double d1 = new Double(NumberFormat.getPercentInstance().parse(percent1).toString()); // 转换的结果是0.6789
        Double d2 = new Double(NumberFormat.getPercentInstance().parse(percentCompare).toString()); // 转换的结果是0.6789
        if (d1 > d2) {
            return true;
        }
        return false;
    }

    public String getZhenFu(BigDecimal tr, BigDecimal openPrice) {
        BigDecimal p = tr.divide(openPrice, 4, BigDecimal.ROUND_HALF_UP);
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(p.doubleValue());
    }

    //获取百分比
    public String getPercent(BigDecimal nowPrice, BigDecimal lastPrice) {
        try {
            BigDecimal d = nowPrice.subtract(lastPrice);
            //BigDecimal b = d.setScale(4, RoundingMode.HALF_UP);//保留4位小数
            BigDecimal p = d.divide(lastPrice, 4, BigDecimal.ROUND_HALF_UP);
            NumberFormat percent = NumberFormat.getPercentInstance();
            percent.setMaximumFractionDigits(2);
            return percent.format(p.doubleValue());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "0%";
    }


    //减法
    public BigDecimal subs(BigDecimal d1, BigDecimal d2) {
        return d1.subtract(d2).setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal chufa(BigDecimal vol1, BigDecimal vol2) {
        try {
            BigDecimal d2 = vol1.divide(vol2, 4, RoundingMode.HALF_UP);
            return d2;
        } catch (Exception e) {
            return new BigDecimal("0");
        }
    }

    //乘法
    public BigDecimal mul(BigDecimal d1, BigDecimal d2) {
        return d1.multiply(d2).setScale(4, RoundingMode.HALF_UP);
    }

    //转化为百分比
    public String thansPercent(BigDecimal nowPrice) {
        try {
            //BigDecimal d = nowPrice.subtract(lastPrice);
            //BigDecimal b = d.setScale(4, RoundingMode.HALF_UP);//保留4位小数
            //BigDecimal p = d.divide(lastPrice, BigDecimal.ROUND_HALF_UP);
            NumberFormat percent = NumberFormat.getPercentInstance();
            percent.setMaximumFractionDigits(2);
            return percent.format(nowPrice);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }

    //百分比*钱
    public BigDecimal countPercent(String percent, BigDecimal d) throws ParseException {
        if (percent.equals("0%")) {
            return d;
        }
        // 接口返回的是Number对象，但是实际是Double类型
        //Double num = (Double) NumberFormat.getInstance().parse(percent); // 转换的结果是67.89
        Double d2 = new Double(NumberFormat.getPercentInstance().parse(percent).toString()); // 转换的结果是0.6789

        return (d.multiply(new BigDecimal(d2))).add(d).setScale(4, BigDecimal.ROUND_HALF_UP);
    }


    //d1 是否大于d2
    public Boolean getFlagDaYu(BigDecimal d1, BigDecimal d2) {
        return d1.compareTo(d2) >= 0;
    }

    //判断是否大于等于0
    public Boolean getFlagDaYu(BigDecimal d1) {
        return d1.compareTo(new BigDecimal("0")) >= 0;
    }


    public BigDecimal getTangLowPrice5m(LimitQueue<KlineXianEntity> limitQueue) {

        List<BigDecimal> list = new ArrayList<>();
        Queue<KlineXianEntity> queue = limitQueue.getQueue();
        for (KlineXianEntity en5m : queue) {
            list.add(en5m.getLowPrice());
        }
        BigDecimal dd = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (getFlagDaYu(dd, list.get(i))) {
                dd = list.get(i);
            }
        }
        return dd;
    }

    public BigDecimal getTangHighPrice5m(LimitQueue<KlineXianEntity> limitQueue) {
        List<BigDecimal> list = new ArrayList<>();
        Queue<KlineXianEntity> queue = limitQueue.getQueue();
        for (KlineXianEntity en5m : queue) {
            list.add(en5m.getHighPrice());
        }
        BigDecimal dd = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (getFlagDaYu(list.get(i), dd)) {
                dd = list.get(i);
            }
        }
        return dd;
    }

    public BigDecimal getTR(KlineXianEntity en, BigDecimal lastClosePrice) {
        //TR=∣今日 最高价-最低价
        // 今日最高价-昨收(今日开盘价)
        // 昨日收-今日最低价(今日开盘价)   ∣的最大值
        BigDecimal highPrice = en.getHighPrice();
        BigDecimal lowPrice = en.getLowPrice();

        BigDecimal now = highPrice.subtract(lowPrice);
        BigDecimal now1 = new BigDecimal("0");
        if (getFlagDaYu(highPrice, lastClosePrice)) {
            now1 = highPrice.subtract(lastClosePrice);
        } else {
            now1 = lastClosePrice.subtract(highPrice);
        }
        BigDecimal now2 = new BigDecimal("0");
        if (getFlagDaYu(highPrice, lastClosePrice)) {
            now2 = lowPrice.subtract(lastClosePrice);
        } else {
            now2 = lastClosePrice.subtract(lowPrice);
        }

        BigDecimal big = new BigDecimal("0");
        if (getFlagDaYu(now, now1)) {
            big = now;
        } else {
            big = now1;
        }
        //今日振幅、今日最高与昨收差价，今日最低与昨收差价中的最大值，为真实波幅
        if (getFlagDaYu(now2, big)) {
            big = now2;
        }
        return big;
    }

    //获取平均ATR  N 天数，一般取14
    //平均真实范围
    public BigDecimal getATR(LimitQueue<BigDecimal> limitQueue) {
        List<BigDecimal> list = new ArrayList<>();
        Queue<BigDecimal> queue = limitQueue.getQueue();
        for (BigDecimal en5m : queue) {
            list.add(en5m);
        }
        BigDecimal d = new BigDecimal("0");
        for (int i = 0; i < list.size(); i++) {
            d = d.add(list.get(i));
        }
        BigDecimal dd = d.divide(new BigDecimal(list.size()), 4, BigDecimal.ROUND_HALF_UP);
        return dd;
    }

    //实体是否穿过某条轨道
    public Boolean flagIn(BigDecimal high, BigDecimal low, BigDecimal mid) {
        if (high.compareTo(mid) > 0 && mid.compareTo(low) > 0) {
            return true;
        } else {
            return false;
        }
    }

    //yyyy-mm-yy-hh-mm-ss  String转Date
    public Date getDateStrToDate(String date) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }


    static BigDecimal xi51 = new BigDecimal("0.334");
    static BigDecimal xi52 = new BigDecimal("0.666");

    //EMA（5）= 前一日EMA（5）×2/3＋今日收盘价×1/3
    public BigDecimal getEMA5(BigDecimal todayClose, BigDecimal lastEMA5) {
        BigDecimal m1 = todayClose.multiply(xi51);
        BigDecimal m2 = lastEMA5.multiply(xi52);
        return m1.add(m2);
    }

    static BigDecimal xi71 = new BigDecimal("0.25");
    static BigDecimal xi72 = new BigDecimal("0.75");

    //EMA（7）= 前一日EMA（7）×6/8＋今日收盘价×2/8
    public BigDecimal getEMA7(BigDecimal todayClose, BigDecimal lastEMA7) {
        BigDecimal m1 = todayClose.multiply(xi71);
        BigDecimal m2 = lastEMA7.multiply(xi72);
        return m1.add(m2);
    }

    static BigDecimal xi121 = new BigDecimal("0.1538");
    static BigDecimal xi122 = new BigDecimal("0.8462");

    //EMA（12）= 前一日EMA（12）×11/13＋今日收盘价×2/13
    public BigDecimal getEMA12(BigDecimal todayClose, BigDecimal lastEMA12) {
        BigDecimal m1 = todayClose.multiply(xi121);
        BigDecimal m2 = lastEMA12.multiply(xi122);
        return m1.add(m2);
    }

    BigDecimal xi201 = new BigDecimal("0.0953");
    BigDecimal xi202 = new BigDecimal("0.9047");

    //EMA（26）= 前一日EMA（20）×19/21＋今日收盘价×2/21
    public BigDecimal getEMA20(BigDecimal todayClose, BigDecimal lastEMA20) {
        BigDecimal m1 = todayClose.multiply(xi201);
        BigDecimal m2 = lastEMA20.multiply(xi202);
        return m1.add(m2);
    }


    static BigDecimal xi261 = new BigDecimal("0.0741");
    static BigDecimal xi262 = new BigDecimal("0.9259");

    //EMA（26）= 前一日EMA（26）×25/27＋今日收盘价×2/27
    public BigDecimal getEMA26(BigDecimal todayClose, BigDecimal lastEMA26) {
        BigDecimal m1 = todayClose.multiply(xi261);
        BigDecimal m2 = lastEMA26.multiply(xi262);
        return m1.add(m2);
    }

    static BigDecimal xi601 = new BigDecimal("0.0328");
    static BigDecimal xi602 = new BigDecimal("0.9672");

    //EMA（60）= 前一日EMA（26）×59/61＋今日收盘价×2/61
    public BigDecimal getEMA60(BigDecimal todayClose, BigDecimal lastEMA60) {
        BigDecimal m1 = todayClose.multiply(xi601);
        BigDecimal m2 = lastEMA60.multiply(xi602);
        return m1.add(m2);
    }


    //EMA（120）= 前一日EMA（120）×119/121＋今日收盘价×2/121
    static BigDecimal xi1201 = new BigDecimal("0.0165");
    static BigDecimal xi1202 = new BigDecimal("0.9835");

    public BigDecimal getEMA120(BigDecimal todayClose, BigDecimal lastEMA60) {
        BigDecimal m1 = todayClose.multiply(xi1201);
        BigDecimal m2 = lastEMA60.multiply(xi1202);
        return m1.add(m2);
    }

    static BigDecimal xiDea1 = new BigDecimal("0.2");
    static BigDecimal xiDea2 = new BigDecimal("0.8");

    public BigDecimal getDEA(BigDecimal todayDif, BigDecimal lastDEA) {
        BigDecimal m1 = todayDif.multiply(xiDea1);
        BigDecimal m2 = lastDEA.multiply(xiDea2);
        return m1.add(m2);
    }


    public BigDecimal getEMASca(BigDecimal todayClose, BigDecimal todayOPen, BigDecimal lastEMA, int type) {
        BigDecimal d1 = new BigDecimal("0");
        if (20 == type) {
            d1 = getEMA20(todayClose, lastEMA);
        } else if (60 == type) {
            d1 = getEMA60(todayClose, lastEMA);
        } else if (12 == type) {
            d1 = getEMA12(todayClose, lastEMA);
        } else if (26 == type) {
            d1 = getEMA26(todayClose, lastEMA);
        } else if (120 == type) {
            d1 = getEMA120(todayClose, lastEMA);
        }
        int s = todayClose.stripTrailingZeros().scale();
        int s1 = todayOPen.stripTrailingZeros().scale();
        if (s != s1) {
            if (s < s1) {
                s = s1;
            }
        }
        d1 = d1.setScale(s, BigDecimal.ROUND_HALF_UP);
        return d1;
    }

    public BigDecimal getEMA60Sca(BigDecimal todayClose, BigDecimal lastEMA60, String symbol) {
        BigDecimal d1 = getEMA60(todayClose, lastEMA60);
        //BigDecimal dd = setSca( symbol, d1);
        return d1;
    }


    //查找极点,极点重复 9根k线确定一个极点
    public List<KlineXianEntity> setPoleType1H(List<KlineXianEntity> list) {
        List<KlineXianEntity> updateList = new ArrayList<>();
        for (int i = 0; i < list.size() - 9; i++) {
            KlineXianEntity e8 = list.get(i + 8);
            KlineXianEntity e7 = list.get(i + 7);
            KlineXianEntity e6 = list.get(i + 6);

            KlineXianEntity e5 = list.get(i + 5);//e5是极高点 ;或者极低点
            KlineXianEntity e4 = list.get(i + 4);
            KlineXianEntity e3 = list.get(i + 3);

            KlineXianEntity e2 = list.get(i + 2);
            KlineXianEntity e1 = list.get(i + 1);
            KlineXianEntity e0 = list.get(i);

            if (getFlagDaYu(e5.getHighPrice(), e0.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e1.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e2.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e3.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e4.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e6.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e7.getHighPrice()) &&
                    getFlagDaYu(e5.getHighPrice(), e8.getHighPrice())
            ) {
                System.out.println("极高点:" + dateToString(e5.getOpenTime()));
                e5.setPoleType(1);
                updateList.add(e5);
            }

            if (getFlagDaYu(e0.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e1.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e2.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e3.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e4.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e6.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e7.getLowPrice(), e5.getLowPrice()) &&
                    getFlagDaYu(e8.getLowPrice(), e5.getLowPrice())
            ) {
                System.out.println("极低点:" + dateToString(e5.getOpenTime()));
                e5.setPoleType(2);
                updateList.add(e5);
            }
        }
        return updateList;
    }


    //获取一天最高波动的票



    static BigDecimal hundred = new BigDecimal("100");
    static BigDecimal one = new BigDecimal("1");

    static BigDecimal zero = new BigDecimal("0");
    //       2/n+1 ,n-1/n+1
    //static BigDecimal RSI5xi1 = new BigDecimal("0.2");
    //static BigDecimal RSI5xi2 = new BigDecimal("0.8");

    //static BigDecimal xi51 = new BigDecimal("0.334");
    //static BigDecimal xi52 = new BigDecimal("0.666");

    //en 当前，list  前面n个  只计算rsi5或者rsi7
    //计算偏差过大
    public void getRSI(LimitQueue<KlineXianEntity> limitQueue, KlineXianEntity entity) {
        List<KlineXianEntity> list = new ArrayList<>();
        for (KlineXianEntity e : limitQueue) {
            list.add(e);
        }
        BigDecimal allZ = new BigDecimal("0");
        BigDecimal allD = new BigDecimal("0");


        for (int i = 0; i < list.size(); i++) {
            KlineXianEntity en = list.get(i);
            BigDecimal d = subs(en.getOpenPrice(), en.getClosePrice()).abs();
            if (en.getPercent().contains("-")) {
                //跌
                allD = allD.add(d);
            } else {
                //涨
                allZ = allZ.add(d);
            }
        }


        //43.84
        //计算简单平均
        BigDecimal rs = chufa(allZ, allD);
        //100×RS/(1+RS);
        BigDecimal dd = chufa(rs, rs.add(one));
        //entity.setRsi(mul(hundred, dd));
        //entity.setRs5z(chufa(allZ, ss));
        //entity.setRs5d(chufa(allD, ss));


        //KlineXianEntity en0 = list.get(list.size() - 1);

        //https://zhuanlan.zhihu.com/p/537741277
        //https://zhuanlan.zhihu.com/p/677239613
    }


    // RS = n 日内收盘涨幅合计/n 日内收盘跌幅合计
    //RS = (Avg Gain)/(Avg Loss) = 3.13/2.52 = 1.24。
    //RSI=100－100÷（1+RS）


    //计算滑动平均
    //BigDecimal size = new BigDecimal(list.size());


    //BigDecimal rs = xi1;
    //[（以前的平均增益）*13）+ 当前增益）]/14。


    //mul(size.subtract(one),allD);

    //RS= 平滑收益均值/平滑损失均值

    //BigDecimal rs = avgZ.divide(avgD, 4, BigDecimal.ROUND_HALF_UP);


}

