package com.example.rich.entity.xian;

import com.example.rich.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class KlineXianEntity extends BaseEntity {
    // 开盘价
    private BigDecimal openPrice;
    // 收盘价(当前K线未结束的即为最新价)
    private BigDecimal closePrice;
    // 开盘时间
    private Date openTime;
    // 最高价
    private BigDecimal highPrice;
    // 最低价
    private BigDecimal lowPrice;
    // 成交量
    private BigDecimal volume;
    // 收盘时间
    private Date closeTime;
    // 成交额
    private BigDecimal volumeMoney;
    // 主动买入成交量
    private BigDecimal initiativeVolume;
    // 成交笔数
    private Long volumeNumber;
    // 主动买入成交额
    private BigDecimal initiativeMoney;

    private String symbol;

    private String percent;

    private BigDecimal ema5;
    private BigDecimal ema10;
    private BigDecimal ema20;

    private BigDecimal ema60;
    private BigDecimal ema120;
    private BigDecimal bollup;
    private BigDecimal bolldown;

    private BigDecimal tr;
    private BigDecimal atrl;
    private BigDecimal atrs;
    private String zhengfu;

    private BigDecimal ema12;
    private BigDecimal ema26;
    private BigDecimal dif;//快线
    private BigDecimal dea;//慢线
    private BigDecimal macd;//柱子



    //1 极高点 ;-1 极低点 ; 0 普通点
    private int poleType;


//    private BigDecimal rsi;
    //private BigDecimal rs5z;
    //private BigDecimal rs5d;

}
