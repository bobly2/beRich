package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/3/24 11:59
 * @Version: 1.0
 */
@Data
public class AggResponseDto {

    private String symbol;
    // 归集成交ID
    private Long a;
    // 成交价
    private String p;
    // 成交量
    private String q;
    private Long f;
    private Long l;
    private Long T;
    private Boolean m;
    private Boolean M;
}
