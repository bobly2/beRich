package com.example.rich.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VolMaEntity {
    private BigDecimal vma5;
    private BigDecimal vma10;
    private BigDecimal vma30;
    private BigDecimal vma60;
    private String symbol;
    private Date closeTime;
    private Long id;
    private BigDecimal closePrice;
    private BigDecimal vol;
}
