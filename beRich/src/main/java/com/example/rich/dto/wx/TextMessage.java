package com.example.rich.dto.wx;

import lombok.*;
import org.springframework.beans.BeanUtils;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/21 13:57
 * @Version: 1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class TextMessage extends WxMessage {

    private String Content;// 文本消息内容


    //用来把基类的属性值复制给子类
    public static TextMessage adapt(WxMessage msg) {
        TextMessage textMessage = new TextMessage();
        BeanUtils.copyProperties(msg, textMessage);
        return textMessage;
    }
}