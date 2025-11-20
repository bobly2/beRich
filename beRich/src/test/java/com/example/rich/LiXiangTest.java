package com.example.rich;

import com.example.rich.entity.u.KlineUEntity;
import com.example.rich.entity.xian.KlineXianEntity;
import com.example.rich.enums.TableNameEnum;
import com.example.rich.mapper.u.BaseUMapper;
import com.example.rich.utils.ComputeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class LiXiangTest {
    @Autowired
    private ComputeUtils computeUtils;
    @Autowired
    private BaseUMapper baseUMapper;
    //130 1% -1.3
    //2% 36次,
    //3% 24次,
    //4% 18次
    //5% 15次
    //10% 8次


    //30%   20次
    //策略1  目标 ：   盈亏1：1 ，胜率   80% ， 短线操作   基于5m ,符合所有币种
    @Test
    public void lixiang() throws ParseException {
        BigDecimal benjin = new BigDecimal("100");
        String str = "1.5%";
        for (int i = 1; i < 120; i++) {
            benjin = computeUtils.countPercent(str, benjin);
            System.out.println("第" + i + "次,本金:" + benjin);
        }
        // 15次 1% 一次 6%
        //第119次,本金:588.1098
    }

    //25.38%  21.71%

    //24 * 5 = 120


    //抓住所有波段！ 一月    100->5672.9553  翻了50倍
    //1月   只考虑空的波段  988.5568   10倍  -> 再考虑到失误，杠杆。   16 - 8
    // 3天100%



    @Test
    public void percent() throws ParseException {
        List<KlineXianEntity> list = new ArrayList<>();

        list.add(setNewEn2("0.5329-0.4466"));
        //list.add(setNewEn2("0.4569-0.5943"));
        list.add(setNewEn2("0.5698-0.4803"));

        list.add(setNewEn2("0.5042-0.3743"));
        list.add(setNewEn2("0.4092-0.3366"));
        list.add(setNewEn2("0.3886-0.3436"));
        list.add(setNewEn2("0.4070-0.3080"));
        //list.add(setNewEn2("0.3212-0.3638"));

        list.add(setNewEn2("0.3646-0.3090"));
        //list.add(setNewEn("0.3103", "0.3353"));

        list.add(setNewEn("0.3378", "0.3108"));
        //list.add(setNewEn("0.3098", "0.4432"));
        list.add(setNewEn("0.4275", "0.3845"));
        list.add(setNewEn("0.4189", "0.3703"));

        //list.add(setNewEn("0.3703", "0.4188"));

        list.add(setNewEn2("0.4188-0.3804"));
        //list.add(setNewEn2("0.3768-0.4030"));
        list.add(setNewEn2("0.4017-0.3060"));
        //list.add(setNewEn2("0.3121-0.3346"));
        list.add(setNewEn2("0.3318-0.3080"));
        //list.add(setNewEn2("0.3047-0.3492"));

        list.add(setNewEn2("0.3427-0.2776"));
        //list.add(setNewEn2("0.2733-0.3260"));
        list.add(setNewEn2("0.3246-0.2988"));
        //list.add(setNewEn2("0.2987-0.3400"));
        //list.add(setNewEn2("0.3217-0.3394"));

        list.add(setNewEn2("0.3440-0.3219"));
        //list.add(setNewEn2("0.3213-0.3673"));
        list.add(setNewEn2("0.3632-0.3442"));
        //list.add(setNewEn2("0.3442-0.3564"));
        list.add(setNewEn2("0.3574-0.3132"));

        //2月
        //list.add(setNewEn2("0.3147-0.3282"));
        //list.add(setNewEn2("0.3288-0.3198"));
        list.add(setNewEn2("0.3244-0.3111"));
        //list.add(setNewEn2("0.3132-0.3196"));
        //list.add(setNewEn2("0.3047-0.3248"));

        //list.add(setNewEn2("0.3107-0.3564"));
        //list.add(setNewEn2("0.3327-0.3884"));
        list.add(setNewEn2("0.3971-0.3657"));
        //list.add(setNewEn2("0.3633-0.4498"));
        list.add(setNewEn2("0.4265-0.4141"));

        //list.add(setNewEn2("0.41-0.4412"));
        list.add(setNewEn2("0.4338-0.3918"));
        //list.add(setNewEn2("0.3943-0.4254"));
        list.add(setNewEn2("0.4227-0.3642"));
        //list.add(setNewEn2("0.3642-0.4063"));

        list.add(setNewEn2("0.4063-0.3713"));
        //list.add(setNewEn2("0.3713-0.4149"));
        //list.add(setNewEn2("0.3757-0.4149"));
        //list.add(setNewEn2("0.3772-0.4442"));
        list.add(setNewEn2("0.4404-0.4029"));

        //list.add(setNewEn2("0.4040-0.4171"));

        BigDecimal benjin = new BigDecimal("100");
        for (int i = 0; i < list.size(); i++) {
            KlineXianEntity en = list.get(i);
            BigDecimal open = en.getOpenPrice();
            BigDecimal close = en.getClosePrice();
            String percent = computeUtils.getPercent(close, open);
//            if(percent.contains("-")){
//                percent=percent.replace("-","");
//            }else{
//                percent="0%";
//            }
            percent=percent.replace("-","");
//            if(percent.contains("-")){
//                percent="0%";
//            }
            benjin = computeUtils.countPercent(percent, benjin);
            System.out.println( "：" + percent+",benjin:"+benjin);
        }


    }

    public KlineXianEntity setNewEn(String openPrice, String closePrice) {
        KlineXianEntity en = new KlineXianEntity();
        en.setOpenPrice(new BigDecimal(openPrice));
        en.setClosePrice(new BigDecimal(closePrice));
        return en;
    }

    public KlineXianEntity setNewEn2(String openPrice) {
        String[] arr = openPrice.split("-");
        KlineXianEntity en = new KlineXianEntity();
        en.setOpenPrice(new BigDecimal(arr[0]));
        en.setClosePrice(new BigDecimal(arr[1]));
        return en;
    }



}