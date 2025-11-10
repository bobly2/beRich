package com.example.rich.entity;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/9 17:44
 * @Version: 1.0
 */
@Data
public class SymbolEntity {
    //名字
    private String symbol;
    //币种判断权重,
    private Integer weight;

}
