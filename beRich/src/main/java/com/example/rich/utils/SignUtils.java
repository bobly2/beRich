package com.example.rich.utils;

import com.example.rich.entity.BeiLiEntityDto;
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









}
