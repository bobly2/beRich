package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 17:20
 * @Version: 1.0
 */
@Data
public class BestGuaResponseDto {
    private String symbol;
    private String bidPrice;
    private String bidQty;
    private String askPrice;
    private String askQty;
}
