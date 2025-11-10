package com.example.rich.enums;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/15 9:51
 * @Version: 1.0
 */
public enum TimeInForceEnums {
    GTC("GTC"),//成交为止订单会一直有效，直到被成交或者取消
    FOK("FOK"),//无法全部立即成交就撤销如果无法全部成交，订单会失效。
    IOC("IOC");//无法立即成交的部分就撤销订单在失效前会尽量多的成交。
    private String timeInForce;


    public String getTimeInForce() {
        return timeInForce;
    }

    // 构造方法
    private TimeInForceEnums(String timeInForce) {

        this.timeInForce = timeInForce;
    }
}
