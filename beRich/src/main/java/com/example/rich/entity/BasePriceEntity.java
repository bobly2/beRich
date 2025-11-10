package com.example.rich.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BasePriceEntity {
    private String symbol;
    private BigDecimal lastHighPrice1;
    private BigDecimal lastHighPrice2;
    private BigDecimal lastHighPrice3;
    private BigDecimal lastHighPrice4;
    private BigDecimal lastHighPrice5;
    private BigDecimal lastLowPrice1;
    private BigDecimal lastLowPrice2;
    private BigDecimal lastLowPrice3;
    private BigDecimal lastLowPrice4;
    private BigDecimal lastLowPrice5;
    private Date openTime;

    //上区间线
    private BigDecimal upLineUp;
    private BigDecimal upLineDown;
    //下区间线
    private BigDecimal downLineUp;
    private BigDecimal downLineDown;

    //昨日幅度
    private BigDecimal amplitude;
}
