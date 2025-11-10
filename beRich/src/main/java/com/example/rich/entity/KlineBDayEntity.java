package com.example.rich.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/8 11:43
 * @Version: 1.0
 */
@Data
public class KlineBDayEntity extends BaseEntity {
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



}
