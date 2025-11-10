package com.example.rich.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: SC19002999
 * @Description:深度返回
 * @Date: 2021/12/30 16:01
 * @Version: 1.0
 */
@Data
public class DepthResponseDto {
    private Long lastUpdateId;
    // 价位-挂单量
    private List<List<String>> bids;
    private List<List<String>> asks;
}
