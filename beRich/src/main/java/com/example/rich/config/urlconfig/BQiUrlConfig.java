package com.example.rich.config.urlconfig;

/**
 * @Author: SC19002999
 * @Description:币本位
 * @Date: 2021/12/30 14:31
 * @Version: 1.0
 */
public class BQiUrlConfig {
    //响应中如有数组,数组元素以时间升序排列,越早的数据越提前。
    public static final String B_Base_Url = "https://dapi.binance.com";

    //获取时间
    public static final String B_GET_time = "/dapi/v1/time";

    //深度信息
    public static final String B_GET_Deep = "/dapi/v1/depth";

    //近期成交 仅返回订单簿成交，即不会返回保险基金和自动减仓(ADL)成交
    public static final String B_Least_Trad = "/dapi/v1/trades";

    //查询历史成交 (MARKET_DATA)
    public static final String B_History_Trad = "/dapi/v1/historicalTrades";

    //近期成交(归集)
    public static final String B_Least_All_Trad = "/dapi/v1/aggTrades";

    //最新现货指数价格和Mark Price
    public static final String B_GET_ZHISHU = "/dapi/v1/premiumIndex";
    //获取k线
    public static final String B_GET_Kline = "/dapi/v1/klines";

    //连续合约K线数据
    public static final String B_Get_LianXu_Kline = "/dapi/v1/continuousKlines";

    //价格指数K线数据
    public static final String B_Get_Price_ZhiShu_Kline = "/dapi/v1/indexPriceKlines";

    //标记价格K线数据
    public static final String B_Get_Price_BiaoJi_Kline = "/dapi/v1/indexPriceKlines";

    //24hr价格变动情况
    public static final String B_24h_Price = "/dapi/v1/ticker/24hr";

    //获取未平仓合约数
    public static final String B_Not_Ping_Number = "/dapi/v1/openInterest";

    //合约持仓量
    public static final String B_Have_Ping_Number = "/dapi/v1/openInterestHist";

    //大户账户数多空比
    public static final String B_DaHu_Count_DuoKong = "/futures/data/topLongShortAccountRatio";

    //大户持仓量多空比
    public static final String B_DaHu_Have_DuoKong = "/futures/data/topLongShortAccountRatio";

    //多空持仓人数比
    public static final String B_DuoKong_People = "/futures/data/globalLongShortAccountRatio";

    //合约主动买卖量
    public static final String B_HeYue_Number = "/futures/data/takerBuySellVol";

    //基差
    public static final String B_Ji_Cha = "/futures/data/basis";
}
