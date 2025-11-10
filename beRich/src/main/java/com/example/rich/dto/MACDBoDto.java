package com.example.rich.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/24 10:38
 * @Version: 1.0
 */
@Data
public class MACDBoDto {

    private BigDecimal ema12;
    private BigDecimal ema26;

    //上涨，dif变大，下跌，降低，甚至为负
    //快速线
    private BigDecimal dif;

    //反转信号-离职差的9日平均线  离差平均值  此值又名 此值又名DEA或DEM
    //慢速线
    private BigDecimal dea;

    private BigDecimal ZhuTu;
}
