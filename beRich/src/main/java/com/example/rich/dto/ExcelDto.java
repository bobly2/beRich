package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/3/29 17:08
 * @Version: 1.0
 */
@Data
public class ExcelDto {
    private String time;
    private String price;
    private String percent;
    //成交量 幅度
    private String vp;
    //金额  幅度
    private String mp;
    //成交金额
    private String volM;
    //净流入
    private String intVol;
    //平均成交-30
    private String pv;

}
