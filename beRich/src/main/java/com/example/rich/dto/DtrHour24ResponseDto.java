package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 17:01
 * @Version: 1.0
 */
@Data
public class DtrHour24ResponseDto {

    private String symbol;
    //变化幅度
    private String priceChange;
    //百分比
    private String priceChangePercent;
    //加权平均价格
    private String weightedAvgPrice;
    //上个交易日之收盘价
    private String prevClosePrice;

    //最后报价
    private String lastPrice;
    //最后交易量
    private String lastQty;

    //挂牌价
    private String bidPrice;
    //挂单量
    private String bidQty;
    //叫价
    private String askPrice;

    //叫价量
    private String askQty;
    //开盘价
    private String openPrice;
    //最高价
    private String highPrice;
    //最低价
    private String lowPrice;
    //成交量
    private String volume;

    //报价量
    private String quoteVolume;

    private Long openTime;
    private Long closeTime;
    // 首笔成交id
    private Long firstId;
    // 末笔成交id
    private Long lastId;

    // 成交笔数
    private Long count;
}
