package com.example.rich.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/21 10:15
 * @Version: 1.0
 */
@Data
public class MaEntity {
    private BigDecimal ma5;
    private BigDecimal ma10;
    private BigDecimal ma30;
    private String symbol;
    private Date closeTime;
    private Long id;
}
