package com.example.rich.dto.request;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/15 9:47
 * @Version: 1.0
 */
@Data
public class OrderResponseDto {
    private String symbol;
    private Long orderId;
    private Long orderListId;
    private String clientOrderId;
    private String transactTime;
    private String price;
    private String origQty;
    private String executedQty;
    private String cummulativeQuoteQty;

    private String status;
    private String timeInForce;
    private String type;
    private String side;
/*     "symbol":"BTCUSDT",
             "orderId":28,
             "orderListId":-1, // OCO订单ID，否则为 -1
             "clientOrderId":"6gCrw2kRUAF9CvJDGP16IP",
             "transactTime":1507725176595,
             "price":"0.00000000",
             "origQty":"10.00000000",
             "executedQty":"10.00000000",
             "cummulativeQuoteQty":"10.00000000",
             "status":"FILLED",
             "timeInForce":"GTC",
             "type":"MARKET",
             "side":"SELL"*/
}
