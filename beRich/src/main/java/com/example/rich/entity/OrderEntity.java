package com.example.rich.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderEntity extends BaseEntity implements Serializable {
    private BigDecimal buyPrice;
    private BigDecimal salesPrice;
    private BigDecimal profit;
    private Boolean flagProfit;
    private BigDecimal allMoney;
}
