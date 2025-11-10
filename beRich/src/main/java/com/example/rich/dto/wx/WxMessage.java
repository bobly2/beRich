package com.example.rich.dto.wx;

import lombok.*;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/21 13:56
 * @Version: 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxMessage {
    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link）text
    private String MsgType;
    // 消息id，64位整型
    private long MsgId;


}
