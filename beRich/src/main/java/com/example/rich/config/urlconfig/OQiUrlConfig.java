package com.example.rich.config.urlconfig;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 14:34
 * @Version: 1.0
 */
public class OQiUrlConfig {
    public static final String Base_Url = "https://vapi.binance.com";

    public static final String O_GET_time = "/vapi/v1/time";

    //现货指数价格获取
    public static final String O_GET_ZHISHU = "/vapi/v1/index";

    //深度信息
    public static final String O_GET_Deep = "/vapi/v1/index";

    //获取k线
    public static final String O_GET_Kline = "/vapi/v1/klines";

    //期权近期成交
    public static final String O_Least_Trad = "/vapi/v1/trades";

}
