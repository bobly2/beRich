package com.example.rich.controller;

import com.example.rich.config.urlconfig.XianUrlConfig;
import com.example.rich.dto.wx.TextMessage;
import com.example.rich.dto.wx.WxMessage;

import com.example.rich.utils.WeiXinPublicUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2022/2/21 11:04
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/wx")
public class WxController {
    @Autowired(required = true)
    private WeiXinPublicUtils weiXinPublicUtils;



    @PostMapping("/verify")
    @ResponseBody
    public String receiveMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1. 获取微信服务器发送的消息，转换成map对象
        Map<String, String> map = weiXinPublicUtils.parseXmlToMap(request);
        log.info(map.toString());
        // 2. 获取详细的信息
        // 发送方帐号（open_id）
        String fromUserName = map.get("FromUserName");
        // 公众帐号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        // 消息内容
        String content = map.get("Content");
        // 消息id
        String msgId = map.get("MsgId");
        //3. 定义回复消息对象
        String respMsg = "";
        // 也可以用new，然后一个属性一个属性的set
        // 微信消息的基类
        //set属性值的时候，注意：ToUserName 和 FromUserName的值要反过来！是坑!是坑!是坑!
        WxMessage msg = WxMessage.builder().FromUserName(toUserName).
                ToUserName(fromUserName).MsgType(msgType).MsgId(Long.parseLong(msgId))
                .CreateTime(System.currentTimeMillis()).build();

        Boolean bool = true;
        //要回复的消息内容
        String resultContent = "";
        if (bool) {
            resultContent = "关注已更改:success";
        } else {
            resultContent = "失败,爱你么么哒";
        }
        TextMessage textMessage = TextMessage.adapt(msg);
        textMessage.setContent(resultContent);
        respMsg = weiXinPublicUtils.parseMsgToXml(textMessage, TextMessage.class);
        return respMsg;
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        ArrayList<String> array = new ArrayList<String>();
        array.add(signature);
        array.add(timestamp);
        array.add(nonce);
        //排序
        String sortString = weiXinPublicUtils.sort(XianUrlConfig.WeiXinToken, timestamp, nonce);
        //加密
        String mytoken = weiXinPublicUtils.getSHA1(sortString);
        //校验签名
        if (mytoken != null && mytoken != "" && mytoken.equals(signature)) {
            response.getWriter().println(echostr); //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else {
            return "";
        }
    }
}

