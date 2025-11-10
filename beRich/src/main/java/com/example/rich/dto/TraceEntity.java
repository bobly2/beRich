package com.example.rich.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TraceEntity {
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private Date buyTime;
    private Date sellTime;
}
