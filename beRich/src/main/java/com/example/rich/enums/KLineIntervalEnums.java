package com.example.rich.enums;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/2 11:53
 * @Version: 1.0
 */
public enum KLineIntervalEnums {
    m1("1m","1m" ),
    m3("3m","3m"),
    m5("5m","5m"),
    m15("15m","15m"),
    m30("30m","30m"),
    h1("1h","1h"),
    h2("2h","2h"),
    h4("4h","4h"),
    h8("8h","8h"),
    h12("12h","12h"),
    d1("1d","1d"),
    d3("3d","3d"),
    w1("1w","1w"),
    month1("1M","1M");

    private String interval;
    private String type;
    KLineIntervalEnums( String type,String interval) {
        this.interval = interval;
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }
}
