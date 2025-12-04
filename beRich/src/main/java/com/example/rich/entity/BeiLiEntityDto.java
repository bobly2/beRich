package com.example.rich.entity;

import com.example.rich.entity.u.KlineUEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BeiLiEntityDto {
    private String symbol;
    private String type;
    private BigDecimal nowprice;
    private String percent;
    private Date startDate;

    private Date firstDate;
    private Date secondDate;
    private Date thirdDate;

    private KlineUEntity startEn;
    private BigDecimal HighPrice;
    private BigDecimal LowPrice;
}


