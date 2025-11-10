package com.example.rich.config.urlconfig;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/2 10:03
 * @Version: 1.0
 */
public class XianUrlConfig {
    //获取最新价格
    //https://api.binance.com/api/v3/ticker/price?symbol=GALUSDT

    public static final String InitUrl = "https://api.binance.com/api/v3/klines?symbol=%s&interval=%s";
    //合约地址
    public static final String API_BASE_URL = "https://fapi.binance.com";
    //https://api.binance.com/api/v3/time
    public static final String GET_SERVER_TIME = "/api/v3/time";

    //K线数据
    public static final String GET_K = "/fapi/v1/klines";

    //账户信息
    public static final String GET_ACOUNT = "/api/v3/account";

    //获取最新价格
    public static final String GET_PRINCE = "/api/v3/ticker/price";

    //深度信息  https://api.binance.com/api/v3/depth?symbol=BTCUSDT&limit=100
    public static final String Xian_Depth = "/api/v3/depth";

    //近期成交列表
    public static final String Xian_Least_Trades = "/api/v3/trades";

    //查询历史成交 (MARKET_DATA)
    public static final String Xian_History_Trades = "/api/v3/historicalTrades";

    //如果发送startTime和endTime，间隔必须小于一小时
    //近期成交(归集)
    public static final String Xian_Least_ChengJiao = "/api/v3/aggTrades";

    //当前最优挂单
    public static final String Xian_Get_You = "/api/v3/ticker/bookTicker";




    //测试下单
    public static final String Test_Order = "/api/v3/order/test";
    //
    public static final String USDT = "USDT";

    public static final String WeiXinToken = "AAABBBCCC";

}
