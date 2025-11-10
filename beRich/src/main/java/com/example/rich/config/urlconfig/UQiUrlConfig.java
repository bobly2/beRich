package com.example.rich.config.urlconfig;

/**
 * @Author: SC19002999
 * @Description:u本位合约
 * @Date: 2021/12/30 14:28
 * @Version: 1.0
 */
public class UQiUrlConfig {


    //响应中如有数组,数组元素以时间升序排列,越早的数据越提前。
    public static final String U_Base_Url = "https://fapi.binance.com";

    //获取时间
    public static final String U_GET_time = "/fapi/v1/time";

    //深度信息
    public static final String U_GET_Deep = "/fapi/v1/depth";

    //近期成交 仅返回订单簿成交，即不会返回保险基金和自动减仓(ADL)成交
    public static final String U_Least_Trad = "/fapi/v1/trades";

    //查询历史成交 (MARKET_DATA)
    public static final String U_History_Trad = "/fapi/v1/historicalTrades";

    //近期成交(归集)
    public static final String U_Least_All_Trad = "/fapi/v1/aggTrades";

    //最新现货指数价格和Mark Price
    public static final String B_GET_ZHISHU = "/dapi/v1/premiumIndex";
    //获取k线
    public static final String U_GET_Kline = "/fapi/v1/klines";

    //连续合约K线数据
    public static final String U_Get_LianXu_Kline = "/fapi/v1/continuousKlines";

    //价格指数K线数据
    public static final String U_Get_Price_ZhiShu_Kline = "/fapi/v1/indexPriceKlines";

    //标记价格K线数据
    public static final String U_Get_Price_BiaoJi_Kline = "/fapi/v1/markPriceKlines";

    //24hr价格变动情况
    public static final String U_24h_Price = "/fapi/v1/ticker/24hr";

    //最新标记价格和资金费率
    public static final String U_BiaoJi_FeiLv = "/fapi/v1/premiumIndex";

    //最新价格
    public static final String U_Least_Price = "/fapi/v1/ticker/price";

    //获取未平仓合约数
    public static final String U_Not_Ping_Number = "/fapi/v1/openInterest";

    //合约持仓量
    public static final String U_Have_Ping_Number = "/futures/data/openInterestHist";

    //大户账户数多空比
    public static final String U_DaHu_Count_DuoKong = "/futures/data/topLongShortAccountRatio";

    //大户持仓量多空比
    public static final String U_DaHu_Have_DuoKong = "/futures/data/topLongShortPositionRatio";

    //多空持仓人数比
    public static final String U_DuoKong_People = "/futures/data/globalLongShortAccountRatio";

    //合约主动买卖量
    public static final String U_HeYue_Number = "/futures/data/takerlongshortRatio";

    //杠杆代币历史净值K线
    public static final String U_GangGan_Kline = "/fapi/v1/lvtKlines";
    //综合指数交易对信息
    public static final String U_ZongHe_ZhiShu = "fapi/v1/indexInfo";
    //多资产模式资产汇率指数
    public static final String U_Duo_ZiChan_Model_ZhiShu = "/fapi/v1/assetIndex";
}
