package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/2 11:43
 * @Version: 1.0
 */
@Data
public class KLineResponseDto {
    // 开盘时间
    private Long openTime;
    // 开盘价
    private String open;
    // 最高价
    private String high;
    // 最低价
    private String low;
    // 收盘价(当前K线未结束的即为最新价)
    private String close;
    // 成交量
    private String volume;
    // 收盘时间
    private Long closeTime;
    // 成交额
    private String quoteAssetVolume;
    // 成交笔数
    private Long numberOfTrades;
    // 主动买入成交量
    private String takerBuyBaseAssetVolume;
    // 主动买入成交额
    private String takerBuyQuoteAssetVolume;

}

