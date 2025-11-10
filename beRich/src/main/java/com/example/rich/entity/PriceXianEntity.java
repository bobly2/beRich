package com.example.rich.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: SC19002999
 * @Description: 现货1h价格
 * @Date: 2021/12/8 11:40
 * @Version: 1.0
 */
@Data
public class PriceXianEntity {
    private String symbol;
    private BigDecimal price;
    private String percent;
    private Long id;
    private Date time;
    //上涨1 没变0，下跌-1
    private Integer flagUp;
}
