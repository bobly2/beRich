package com.example.rich.enums;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 17:37
 * @Version: 1.0
 */
public enum TypeEnums {

    LIMIT("LIMIT"),//限价单
    MARKET("MARKET"),// 市价单
    STOP_LOSS("STOP_LOSS"),// 止损单
    STOP_LOSS_LIMIT("STOP_LOSS_LIMIT"),// 限价止损单
    TAKE_PROFIT("TAKE_PROFIT"),// 止盈单
    TAKE_PROFIT_LIMIT("TAKE_PROFIT_LIMIT"),// 限价止盈单
    LIMIT_MAKER("LIMIT_MAKER");//限价只挂单
    private String type;

    public String getType() {
        return type;
    }

    // 构造方法
    private TypeEnums(String type) {

        this.type = type;
    }
}
