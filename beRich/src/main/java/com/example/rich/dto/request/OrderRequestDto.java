package com.example.rich.dto.request;

import com.example.rich.enums.SideEnum;
import com.example.rich.enums.TimeInForceEnums;
import com.example.rich.enums.TypeEnums;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/15 9:45
 * @Version: 1.0
 */
@Data
public class OrderRequestDto {
    private String symbol;

    private SideEnum side;//订单方向
    private TypeEnums type;//订单类型
    private TimeInForceEnums timeInForce;//有效方式

    private BigDecimal quantity;//购买数量
    private BigDecimal quoteOrderQty;//订单询价 例如说你想买什么东西，你要给别人下订单，订单就是order，
    // quote order 就是说问你这个订单上的物品的价格
    private BigDecimal price;

    private String newClientOrderId;//客户自定义的唯一订单ID。 如果未发送，则自动生成
    private String stopPrice;//停止价格
    private String icebergQty;
    private String newOrderRespType;
    private String recvWindow;
    private String timestamp;

}
