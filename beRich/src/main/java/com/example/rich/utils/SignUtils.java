package com.example.rich.utils;

import com.example.rich.entity.BeiLiEntityDto;
import com.example.rich.entity.xian.KlineXian1hEntity;
import com.example.rich.entity.xian.KlineXian5mEntity;
import com.example.rich.entity.xian.KlineXianEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
//http://www.danglanglang.com/gupiao/2969

/**
 * @Author: SC19002999
 * @Description: 技术指标
 * @Date: 2021/12/21 13:37
 * @Version: 1.0
 */
@Component
public class SignUtils {
    @Autowired
    private ComputeUtils computeUtils;

    public void getEMA(List<BigDecimal> list) {
        int T = list.size();
        double xishu = 2 / (T + 1);

        // 计算平滑系数
        //滑动平均线

        //EMA（n）=（2 / (n + 1)) * 当日收盘价 + (1 - 2 / (n + 1)) * 上一日EMA
        //当日EMA=α * 当日指数值 + ( 1 - α ) * 昨日指数平均值。其中平滑系数α 为周期单位+1的值的一半。

        //BigDecimal xishu = ;


    }

    //当前K线在短时间内出现了类似正弦曲线的波动时，短周期均线和长周期均线会频繁的相互交差，
    // 在这种情况下，基本上每次都会陪点钱，因为MA算法都是事后诸葛亮，所以出现了这种情况，要么立刻停止交易，
    // 要么找到一些短周期指标加以判读，以减小交易次数


    //MA5 > MA10 5日均线上穿10日均线说明上涨趋势开始，买入
    //MA移动平均线   加起来除以n


    //计算移动平均
    //EMA 指数移动平均值    有加权  指数式递减 的 加权移动平均
    //α * Pricetoday + ( 1 - α ) * EMAyesterday;
    //平滑指数 α  2/n+1
    //eg   x1,x2,x3,x4,x5
    //EMA(X，1)  x1
    //EMA(X，2)  (2/3)*X2+(1/3)X1
    //EMA(X，3)  3/6*X3+2/6*X2+1/6*X1

    //“快”指更短时段的EMA，而“慢”则指较长时段的EMA，最常用的是12及26日EMA
//    public MACDBoDto getMACD(BigDecimal todayClose, BigDecimal lastEMA12, BigDecimal lastEMA26, BigDecimal lastDEA) {
//        BigDecimal ema12 = getEMA12(todayClose, lastEMA12);
//        BigDecimal ema26 = getEMA26(todayClose, lastEMA26);
//
//        MACDBoDto dto = new MACDBoDto();
//        dto.setEma12(ema12);
//        dto.setEma26(ema26);
//
//        BigDecimal dif = ema12.subtract(ema26).setScale(4, BigDecimal.ROUND_HALF_UP);
//        dto.setDif(dif);
//
//        BigDecimal toDayDEA = getEDA(dif, lastDEA).setScale(4, BigDecimal.ROUND_HALF_UP);
//
//        //（DIF-DEA）×2即为MACD柱状图。
//        dto.setZhuTu(((dif.subtract(toDayDEA))).multiply(new BigDecimal("2")));
//        dto.setDea(toDayDEA);
//        return dto;
//    }









        /*

        `    EMA（12）= 前一日EMA（12）×11/13＋今日收盘价×2/13

        EMA（26）= 前一日EMA（26）×25/27＋今日收盘价×2/27

        DIFF=今日EMA（12）- 今日EMA（26）

        DEA（MACD）= 前一日DEA×8/10＋今日DIF×2/10

        BAR=2×(DIFF－DEA)

        第一天：

        DIFF=0, DEA=0, MACD=0

        第二天：

        EMA（12）= 前一日收盘价（12）×11/13＋今日收盘价×2/13

        EMA（26）= 前一日收盘价（26）×25/27＋今日收盘价×2/27

        DIFF=今日EMA（12）- 今日EMA（26）

        DEA（MACD）= 前一日0×8/10＋今日DIF×2/10

        BAR=2×(DIFF－DEA)

        第三天：正常公式`

    * */




/*
         RSI：相对强弱指数（Relative Strength Index），
        是根据一定时期内上涨点数和涨跌点数之和的比率制作出的一种技术曲线。
        能够反映出市场在一定时期内的景气程度。
        超买（买入）极端情况：[0%~10%]  正常情况：[10%~20%]
观望 [30%~70%]
      超卖（卖出）       正常情况：[80%~90%]极端情况：[90%~100%]

    能够反映出市场在一定时期内的景气程度。其N日的伪函数定义如下：

      当短周期均线下穿长周期线的时候，说明整体趋势正在下降，可以卖出。

    当短周期均线上穿长周期线的时候，说明整体趋势正在上长，可以买入。
    如果价格上涨的数量占总变化的比例比较小（大多数情况下降），那随后可能会涨价。
    如果价格上涨的数量占总变化的比例比较大（大多数情况上涨），那随后可能会降价。
    由于计算是通过占比方式来实现的，其值范围是[0,1]，因此适用于编程量化。
    RSI = 100 – 100 / (1 + RS)
    RS = 平均上涨幅度 / 平均下跌幅度
    RS（相对强度）= N日内收盘价涨数和之均值÷N日内收盘价跌数和之均值


    */


    //    如何判断是星十字？
//    开盘价，收盘价在 n以内
//
//    如何判断锤头线
//    实体是下影线的2-3倍以上

    public Boolean flagL(Object ob) {
        KlineXianEntity en = new ObjectMapper().convertValue(ob, KlineXianEntity.class);
        //上影线
        BigDecimal d1 = computeUtils.subs(en.getOpenPrice(), en.getHighPrice()).abs();
        //实体
        BigDecimal d2 = computeUtils.subs(en.getOpenPrice(), en.getClosePrice()).abs();
        //下影线
        BigDecimal d3 = computeUtils.subs(en.getClosePrice(), en.getLowPrice()).abs();
        //上影线大于实体+上影线倍数
        String param = "2";
        //下影线为0
        if (Double.valueOf(d1.toString()) == 0) {
            return false;
        }
        //下影线大于上影线
        if (computeUtils.getFlagDaYu(d3, d1)) {
            return false;
        }
        //实体大于上影线
        if (computeUtils.getFlagDaYu(d2, d1)) {
            return false;
        }
        BigDecimal xia = d3.add(d2);
        if (xia.compareTo(d1) > 0) {
            return false;
        }
        if (Double.valueOf(xia.toString()) == 0) {
            return true;
        }
        BigDecimal bei = d1.divide(xia, 4, BigDecimal.ROUND_HALF_UP);
        if (computeUtils.getFlagDaYu(bei, new BigDecimal(param))) {
            return true;
        } else {
            return false;
        }
    }

    //是否下垂线  T 字型
    public Boolean flagT(Object ob) {
        KlineXianEntity en = new ObjectMapper().convertValue(ob, KlineXianEntity.class);
        try {
            //上影线
            BigDecimal d1 = new BigDecimal("0");
            //实体
            BigDecimal d2 = new BigDecimal("0");
            //下影线
            BigDecimal d3 = new BigDecimal("0");
            if (en.getPercent().contains("-")) {
                //下跌实体

                d1 = computeUtils.subs(en.getOpenPrice(), en.getHighPrice()).abs();
                //实体
                d2 = computeUtils.subs(en.getOpenPrice(), en.getClosePrice()).abs();
                //下影线
                d3 = computeUtils.subs(en.getClosePrice(), en.getLowPrice()).abs();
            } else {
                //上涨实体

                d1 = computeUtils.subs(en.getClosePrice(), en.getHighPrice()).abs();
                //实体
                d2 = computeUtils.subs(en.getOpenPrice(), en.getClosePrice()).abs();
                //下影线
                d3 = computeUtils.subs(en.getOpenPrice(), en.getLowPrice()).abs();
            }
            //上影线

            //下影线大于  实体+上影线  倍数
            String param = "3";
            //下影线为0
            if (Double.valueOf(d3.toString()) == 0) {
                return false;
            }
            //上影线大于下影线
            if (computeUtils.getFlagDaYu(d1, d3)) {
                return false;
            }
            //实体大于下影线
            if (computeUtils.getFlagDaYu(d2, d3)) {
                return false;
            }
            BigDecimal shang = d1.add(d2);
            if (shang.compareTo(d3) > 0) {
                return false;
            }
            if (Double.valueOf(shang.toString()) == 0) {
                return true;
            }
            BigDecimal bei = d3.divide(shang, 4, BigDecimal.ROUND_HALF_UP);
            if (computeUtils.getFlagDaYu(bei, new BigDecimal(param))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("异常id:" + en.getId());
        }
        return false;
    }

    public BeiLiEntityDto flagBeili1H(List<KlineXian1hEntity> list) {

        return null;
    }

    //1h级别找均线，5m级别找m顶，1d
    //1.顺均线操作

    //寻找迅猛上涨或者下跌的k线，寻找之前的波动规律，
    //找到转折点  由多转空的关键点，但是趋势是不会马上转向。
    //市场的一切行为，都是为了耍你
    //在关键位置止损-假突破？真突破，趋势？


    //背离，或者说m顶，关键的是找到极点！
    public BeiLiEntityDto flagBeili(List<KlineXian5mEntity> list) {
        BeiLiEntityDto dto = new BeiLiEntityDto();
        KlineXian5mEntity elast0 = list.get(list.size() - 1);
        KlineXian5mEntity elast1 = list.get(list.size() - 2);//假设为第二个极高点
        KlineXian5mEntity elast2 = list.get(list.size() - 3);
        KlineXian5mEntity elast3 = list.get(list.size() - 4);
        KlineXian5mEntity elast4 = list.get(list.size() - 5);
        KlineXian5mEntity elast5 = list.get(list.size() - 6);
        int poleType3 = 0;
        if (computeUtils.getFlagDaYu(elast1.getHighPrice(), elast0.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast2.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast3.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast4.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast5.getHighPrice())) {
            poleType3 = 2;
        }

        int poleType2 = 0;
        KlineXian5mEntity eMid = new KlineXian5mEntity();
        if (poleType3 == 2) {
            //寻找中间极低点
            for (int i = 4; i < list.size() - 9; i++) {
                KlineXian5mEntity e8 = list.get(i + 8);
                KlineXian5mEntity e7 = list.get(i + 7);
                KlineXian5mEntity e6 = list.get(i + 6);
                KlineXian5mEntity e5 = list.get(i + 5);

                KlineXian5mEntity e4 = list.get(i + 4);//中间的极点

                KlineXian5mEntity e3 = list.get(i + 3);
                KlineXian5mEntity e2 = list.get(i + 2);
                KlineXian5mEntity e1 = list.get(i + 1);
                KlineXian5mEntity e0 = list.get(i);

                if (computeUtils.getFlagDaYu(e0.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e1.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e2.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e3.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e5.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e6.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e7.getLowPrice(), e4.getLowPrice()) &&
                        computeUtils.getFlagDaYu(e8.getLowPrice(), e4.getLowPrice())
                ) {
                    eMid = e4;
                    poleType2 = 1;
                }
            }
        }
        int poleType1 = 0;
        KlineXian5mEntity eFirst = new KlineXian5mEntity();

        if (poleType3 == 2 && poleType2 == 1) {
            //寻找第一个极高点
            for (int i = 0; i < list.size() - 13; i++) {
                KlineXian5mEntity e8 = list.get(i + 3);
                KlineXian5mEntity e7 = list.get(i + 3);
                KlineXian5mEntity e6 = list.get(i + 3);
                KlineXian5mEntity e5 = list.get(i + 3);

                KlineXian5mEntity e4 = list.get(i + 4);//第一个极点

                KlineXian5mEntity e3 = list.get(i + 3);
                KlineXian5mEntity e2 = list.get(i + 2);
                KlineXian5mEntity e1 = list.get(i + 1);
                KlineXian5mEntity e0 = list.get(i);


                //e3的dif 极高点
                if (computeUtils.getFlagDaYu(e4.getHighPrice(), e2.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e1.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e0.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e3.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e5.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e6.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e7.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e4.getHighPrice(), e8.getHighPrice())
                ) {
                    poleType1 = 3;
                    eFirst = e4;
                }

                Long l1 = e3.getOpenTime().getTime();
                Long l2 = elast1.getOpenTime().getTime();
                long diff = (l2 - l1) / (60000);

                if ( computeUtils.getFlagDaYu(elast1.getHighPrice(), e3.getHighPrice())
                        && diff > 40
                        && computeUtils.getFlagDaYu(elast1.getVolumeMoney(), elast2.getVolumeMoney())
                        && computeUtils.getFlagDaYu(elast2.getVolumeMoney(), elast3.getVolumeMoney())
                ) {

                    //出现顶背离

                    //String percent = computeUtils.getPercent(list.get(list.size() - 1).getClosePrice(), elast0.getClosePrice());
//                        dto.setPercent(percent);
//                        System.out.println("第一个点时间：" + computeUtils.dateToString(e3.getOpenTime())
//                                + ",第二个点时间：" + computeUtils.dateToString(elast1.getOpenTime()));
                }

                if(poleType1 == 3
                        && computeUtils.getFlagDaYu(eFirst.getClosePrice(),elast1.getClosePrice())
                ){
                    dto.setSymbol(e3.getSymbol());
                    dto.setType("出现M顶");
                    dto.setNowprice(list.get(list.size() - 1).getClosePrice());
                    dto.setStartDate(elast1.getOpenTime());

                    dto.setFirstDate(eFirst.getOpenTime());
                    dto.setThirdDate(elast1.getOpenTime());
                    dto.setSecondDate(eMid.getOpenTime());
//                    System.out.println("第一个极高点时间：" + computeUtils.dateToString(eFirst.getOpenTime())
//                            + ",第二极高点时间：" + computeUtils.dateToString(elast1.getOpenTime()));
                }

//                String percent = computeUtils.getPercent(list.get(list.size() - 1).getClosePrice(), elast0.getClosePrice());
//                dto.setPercent(percent);

            }
        }
        return dto;
    }


    //背离，或者说m顶，关键的是找到极点！
    public BeiLiEntityDto flagBeili2(List<KlineXian5mEntity> list) {
        BeiLiEntityDto dto = new BeiLiEntityDto();
        KlineXian5mEntity elast0 = list.get(list.size() - 1);
        KlineXian5mEntity elast1 = list.get(list.size() - 2);//假设为第二个极点
        KlineXian5mEntity elast2 = list.get(list.size() - 3);
        KlineXian5mEntity elast3 = list.get(list.size() - 4);
        KlineXian5mEntity elast4 = list.get(list.size() - 5);
        int poleType2 = 0;
        if (elast2.getDif() == null) {
            elast2.setDif(elast2.getClosePrice());
        }
        if (computeUtils.getFlagDaYu(elast1.getHighPrice(), elast0.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast2.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast3.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast4.getHighPrice())
        ) {
            poleType2 = 2;
        }
        //顶背离
        if (poleType2 == 2) {
            //寻找第一个极高点
            for (int i = 0; i < list.size() - 11; i++) {
                KlineXian5mEntity e7 = list.get(i + 7);
                KlineXian5mEntity e6 = list.get(i + 6);
                KlineXian5mEntity e5 = list.get(i + 5);
                KlineXian5mEntity e4 = list.get(i + 4);
                KlineXian5mEntity e3 = list.get(i + 3);//第一个极点
                KlineXian5mEntity e2 = list.get(i + 2);
                KlineXian5mEntity e1 = list.get(i + 1);
                KlineXian5mEntity e0 = list.get(i);
//                //e3的dif 极高点
//                if (computeUtils.getFlagDaYu(e3.getDif(), e2.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e1.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e0.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e4.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e5.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e6.getDif())) {
                if (e0.getDif() == null) {
                    e0.setDif(e0.getClosePrice());
                }
                //e3的dif 极高点
                if (computeUtils.getFlagDaYu(e3.getHighPrice(), e2.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e1.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e0.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e4.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e5.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e6.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e7.getHighPrice())
                        &&
                        computeUtils.getFlagDaYu(e3.getDif(), e2.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e1.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e0.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e4.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e5.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e6.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e7.getDif())
                ) {
                    Long l1 = e3.getOpenTime().getTime();
                    Long l2 = elast1.getOpenTime().getTime();
                    long diff = (l2 - l1) / (60000);
                    if (computeUtils.getFlagDaYu(e3.getDif(), elast1.getDif())
                            && computeUtils.getFlagDaYu(elast1.getHighPrice(), e3.getHighPrice())
                            && diff > 15) {

                        //出现顶背离
                        dto.setSymbol(e3.getSymbol());
                        dto.setType("顶背离");
                        //String percent = computeUtils.getPercent(list.get(list.size() - 1).getClosePrice(), elast0.getClosePrice());
//                        dto.setPercent(percent);
                        System.out.println("第一个点时间：" + computeUtils.dateToString(e3.getOpenTime())
                                + ",第二个点时间：" + computeUtils.dateToString(elast1.getOpenTime()));
                    }
                }
            }
        }
        return dto;
    }


    public BeiLiEntityDto flagBeilih(List<KlineXian1hEntity> list) {
        BeiLiEntityDto dto = new BeiLiEntityDto();
        KlineXian1hEntity elast0 = list.get(list.size() - 1);
        KlineXian1hEntity elast1 = list.get(list.size() - 2);//假设为第二个极点
        KlineXian1hEntity elast2 = list.get(list.size() - 3);
        KlineXian1hEntity elast3 = list.get(list.size() - 4);
        KlineXian1hEntity elast4 = list.get(list.size() - 5);
        int poleType2 = 0;
        if (elast2.getDif() == null) {
            elast2.setDif(elast2.getClosePrice());
        }
        if (computeUtils.getFlagDaYu(elast1.getHighPrice(), elast0.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast2.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast3.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getHighPrice(), elast4.getHighPrice())
                && computeUtils.getFlagDaYu(elast1.getDif(), elast0.getDif())
                && computeUtils.getFlagDaYu(elast1.getDif(), elast2.getDif())
                && computeUtils.getFlagDaYu(elast1.getDif(), elast3.getDif())
                && computeUtils.getFlagDaYu(elast1.getDif(), elast4.getDif())
        ) {
            poleType2 = 2;
        }
//        else if (computeUtils.getFlagDaYu(elast1.getDif(), elast2.getDif()) &&
//                computeUtils.getFlagDaYu(elast0.getDif(), elast2.getDif())) {
//            poleType2 = 1;
//        }
        //顶背离
        if (poleType2 == 2) {
            //寻找第一个极点
            for (int i = 0; i < list.size() - 11; i++) {
                KlineXian1hEntity e7 = list.get(i + 7);
                KlineXian1hEntity e6 = list.get(i + 6);
                KlineXian1hEntity e5 = list.get(i + 5);
                KlineXian1hEntity e4 = list.get(i + 4);
                KlineXian1hEntity e3 = list.get(i + 3);//第一个极点
                KlineXian1hEntity e2 = list.get(i + 2);
                KlineXian1hEntity e1 = list.get(i + 1);
                KlineXian1hEntity e0 = list.get(i);
//                //e3的dif 极高点
//                if (computeUtils.getFlagDaYu(e3.getDif(), e2.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e1.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e0.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e4.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e5.getDif()) &&
//                        computeUtils.getFlagDaYu(e3.getDif(), e6.getDif())) {
                if (e0.getDif() == null) {
                    e0.setDif(e0.getClosePrice());
                }
                //e3的dif 极高点
                if (computeUtils.getFlagDaYu(e3.getHighPrice(), e2.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e1.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e0.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e4.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e5.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e6.getHighPrice()) &&
                        computeUtils.getFlagDaYu(e3.getHighPrice(), e7.getHighPrice())
                        &&
                        computeUtils.getFlagDaYu(e3.getDif(), e2.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e1.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e0.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e4.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e5.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e6.getDif()) &&
                        computeUtils.getFlagDaYu(e3.getDif(), e7.getDif())
                ) {
                    Long l1 = e3.getOpenTime().getTime();
                    Long l2 = elast1.getOpenTime().getTime();
                    long diff = (l2 - l1) / (60000);

                    if (computeUtils.getFlagDaYu(e3.getDif(), elast1.getDif())
                            && computeUtils.getFlagDaYu(elast1.getHighPrice(), e3.getHighPrice())
                            && diff > 15
                            && computeUtils.getFlagDaYu(elast1.getVolumeMoney(), elast2.getVolumeMoney())
                            && computeUtils.getFlagDaYu(elast2.getVolumeMoney(), elast3.getVolumeMoney())
                    ) {

                        //出现顶背离
                        dto.setSymbol(e3.getSymbol());
                        dto.setType("顶背离");
                        //String percent = computeUtils.getPercent(list.get(list.size() - 1).getClosePrice(), elast0.getClosePrice());
//                        dto.setPercent(percent);
//                        System.out.println("第一个点时间：" + computeUtils.dateToString(e3.getOpenTime())
//                                + ",第二个点时间：" + computeUtils.dateToString(elast1.getOpenTime()));
                    }
                }
            }
        }
        //底背离
//        if (poleType2 == 1) {
//            for (int i = 0; i < list.size() - 6; i++) {
//                KlineXian5mEntity e5 = list.get(i + 5);
//                KlineXian5mEntity e4 = list.get(i + 4);
//                KlineXian5mEntity e3 = list.get(i + 3);
//                KlineXian5mEntity e2 = list.get(i + 2);
//                KlineXian5mEntity e1 = list.get(i + 1);
//                KlineXian5mEntity e0 = list.get(i);
//                //e3为dif 极低点
//                if (computeUtils.getFlagDaYu(e2.getDif(), e3.getDif()) &&
//                        computeUtils.getFlagDaYu(e1.getDif(), e3.getDif()) &&
//                        computeUtils.getFlagDaYu(e0.getDif(), e3.getDif()) &&
//                        computeUtils.getFlagDaYu(e4.getDif(), e3.getDif()) &&
//                        computeUtils.getFlagDaYu(e5.getDif(), e3.getDif())) {
//                    e3.setPoleType(1);
//
//                    if (computeUtils.getFlagDaYu(e3.getDif(), elast2.getDif())
//                            && computeUtils.getFlagDaYu(e3.getLowPrice(), elast2.getLowPrice())) {
//                        //出现底背离
//                        dto.setSymbol(e3.getSymbol());
//                        dto.setType("底背离");
//                    }
//
//                }
//            }
//        }

        return dto;
    }
}
