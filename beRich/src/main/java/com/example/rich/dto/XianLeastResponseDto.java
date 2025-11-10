package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:近期成交列表
 * @Date: 2021/12/30 16:31
 * @Version: 1.0
 */
@Data
public class XianLeastResponseDto {
    //成交id
    private Long id;
    //成交价格
    private String price;
    //交易量
    private String qty;

    //报价数量
    private String quoteQty;
    //交易成交时间
    private Long time;
    //是否买方市场
    private Boolean isBuyerMaker;
    //是否最佳合适
    private Boolean isBestMatch;
}
