package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 16:54
 * @Version: 1.0
 */
@Data
public class XianLeastAggTradesResponseDto {
    //归集成交ID
    private Long a;
    // 成交价
    private String p;
    //成交量
    private String q;
    // 被归集的首个成交ID
    private Long f;
    // 被归集的末个成交ID
    private Long l;
    // 成交时间
    private Long T;
    // 是否为主动卖出单
    private Boolean m;
    //是否为最优撮合单(可忽略，目前总为最优撮合)
    private Boolean M;
}
