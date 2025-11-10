package com.example.rich.enums;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 17:34
 * @Version: 1.0
 */
public enum SideEnum {
    BUY("BUY"),
    SELL("SELL");
    private String side;


    public String getSide() {
        return side;
    }

    // 构造方法
    private SideEnum(String side) {

        this.side = side;
    }
}
