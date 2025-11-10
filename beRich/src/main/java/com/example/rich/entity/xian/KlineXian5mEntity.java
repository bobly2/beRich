package com.example.rich.entity.xian;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class KlineXian5mEntity extends KlineXianEntity {
    private BigDecimal ema120;
}
