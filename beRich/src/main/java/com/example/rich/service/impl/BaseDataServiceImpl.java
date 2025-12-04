package com.example.rich.service.impl;


import com.example.rich.config.urlconfig.XianUrlConfig;
import com.example.rich.entity.u.KlineUEntity;
import com.example.rich.enums.KLineIntervalEnums;
import com.example.rich.enums.TableNameEnum;
import com.example.rich.mapper.u.BaseUMapper;
import com.example.rich.service.BaseDataService;
import com.example.rich.utils.ComputeUtils;
import com.example.rich.utils.HttpHelperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/9 17:31
 * @Version: 1.0
 */
@Slf4j
@Service
public class BaseDataServiceImpl implements BaseDataService {
    @Autowired
    private BaseUMapper baseUMapper;
    @Autowired
    private HttpHelperUtils httpHelperUtils;
    @Autowired
    private ComputeUtils computeUtils;
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void initKlineUList(String symbol) {
        String tableName = TableNameEnum.m5.getTableName();
        List<KlineUEntity> list = baseUMapper.getKlienUListForAdd(TableNameEnum.m5.getTableName(), symbol);
        if (CollectionUtils.isEmpty(list)) {
            Long startTime = 1735660800000L;//2025-01-01 00:00:00
            String url = this.setKlineUrlByParam(symbol, KLineIntervalEnums.m5.getInterval(), startTime);
            String str = getDatas(url);

            List<KlineUEntity> dtos = constrctK(str, symbol);
            baseUMapper.insertListKlineUEntity(tableName, dtos);
        } else {
            baseUMapper.deleteById(tableName, list.get(0).getId());
            KlineUEntity en = list.get(1);
            Long startTime = en.getCloseTime().getTime();
            String url = this.setKlineUrlByParam(symbol, KLineIntervalEnums.m5.getInterval(), startTime);
            String str = getDatas(url);
            List<KlineUEntity> dtos = constrctK(str, symbol);
            baseUMapper.insertListKlineUEntity(tableName, dtos);
        }
    }

    public void updateKlineUList(String table, List<KlineUEntity> list) {
        baseUMapper.updateListKlineUEntity(table, list);
    }

    private String setKlineUrlByParam(String symbol, String interval, Long startTime) {
        String url = "";
        if (startTime != 0) {
            url = XianUrlConfig.API_BASE_URL + XianUrlConfig.GET_K + "?symbol=%s&interval=%s&limit=1000"
                    + "&startTime=%s";
            url = String.format(url, symbol, interval, startTime);
        } else {
            //不传时间默认获取1000
            url = XianUrlConfig.API_BASE_URL + XianUrlConfig.GET_K + "?symbol=%s&interval=%s&limit=1000";
            url = String.format(url, symbol, interval);
        }
        return url;
    }


    public String getDatas(String url) {
        String str = httpHelperUtils.getMethods2(url);
        return str;
    }

    public List<KlineUEntity> constrctK(String str, String symbol) {
        List<List<String>> list = null;
        try {
            list = objectMapper.readValue(str, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<KlineUEntity> dtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> object = list.get(i);
            Object s1 = object.get(0);
            Long l1 = Long.valueOf(s1.toString());
            KlineUEntity e1 = new KlineUEntity();
            e1.setSymbol(symbol);
            e1.setOpenTime(new Date(l1));
            e1.setOpenPrice(new BigDecimal(object.get(1)));
            e1.setHighPrice(new BigDecimal(object.get(2)));
            e1.setLowPrice(new BigDecimal(object.get(3)));
            e1.setClosePrice(new BigDecimal(object.get(4)));
            e1.setVolume(new BigDecimal(object.get(5)));
            Object s2 = object.get(6);
            Long l2 = Long.valueOf(s2.toString());
            e1.setCloseTime(new Date(l2));
            e1.setVolumeMoney(new BigDecimal(object.get(7)));
            Object s3 = object.get(8);
            Long l3 = Long.valueOf(s3.toString());
            e1.setVolumeNumber(l3);
            e1.setInitiativeVolume(new BigDecimal(object.get(9)));
            e1.setInitiativeMoney(new BigDecimal(object.get(10)));
            String percent = computeUtils.getPercent(e1.getClosePrice(), e1.getOpenPrice());
            e1.setPercent(percent);
            e1.setTr(e1.getHighPrice().subtract(e1.getLowPrice()).abs());
            String percentzhengfu = computeUtils.getZhenFu(e1.getTr(), e1.getOpenPrice());
            e1.setZhengfu(percentzhengfu);
            dtos.add(e1);
        }
        return dtos;
    }


    public void updateEma2060(String symbol, String table) {
        List<KlineUEntity> list = baseUMapper.getAllByTime(table, symbol, null, null);
        BigDecimal ema20 = list.get(0).getClosePrice();
        BigDecimal ema60 = list.get(0).getClosePrice();
        for (int i = 0; i < list.size() - 1; i++) {
            KlineUEntity e = list.get(i);
            ema20 = computeUtils.getEMASca(e.getClosePrice(), e.getOpenPrice(), ema20, 20);
            e.setEma20(ema20);
            ema60 = computeUtils.getEMASca(e.getClosePrice(), e.getOpenPrice(), ema60, 60);
            e.setEma60(ema60);
        }
        List<List<KlineUEntity>> listmaps = Lists.partition(list, 300);
        for (int i = 0; i < listmaps.size(); i++) {
            baseUMapper.updateListKlineUEntity(table, listmaps.get(i));
            System.out.println(symbol + ":success:" + i);
        }
    }

    @Override
    public void updateMACD(String symbol, String table) {
        List<KlineUEntity> list = baseUMapper.getAllByTime(table, symbol, null, null);
        if (!CollectionUtils.isEmpty(list)) {
            BigDecimal ema20 = list.get(0).getEma20();
            BigDecimal ema60 = list.get(0).getEma60();
            BigDecimal ema12 = list.get(0).getEma12();
            BigDecimal ema26 = list.get(0).getEma26();
            BigDecimal lastDea = list.get(0).getDea();

            if (ema20 == null) {
                ema20 = list.get(0).getClosePrice();
            }
            if (ema60 == null) {
                ema60 = list.get(0).getClosePrice();
            }
            if (ema12 == null) {
                ema12 = list.get(0).getClosePrice();
            }
            if (ema26 == null) {
                ema26 = list.get(0).getClosePrice();
            }

            for (int i = 0; i < list.size(); i++) {
                KlineUEntity e = list.get(i);
                try {
                    ema20 = computeUtils.getEMASca(e.getClosePrice(), e.getOpenPrice(), ema20, 20);
                    e.setEma20(ema20);
                    ema60 = computeUtils.getEMASca(e.getClosePrice(), e.getOpenPrice(), ema60, 60);
                    e.setEma60(ema60);
                    ema12 = computeUtils.getEMASca(e.getClosePrice(), e.getOpenPrice(), ema12, 12);
                    e.setEma12(ema12);
                    ema26 = computeUtils.getEMASca(e.getClosePrice(), e.getOpenPrice(), ema26, 26);
                    e.setEma26(ema26);

                    e.setDif(computeUtils.subs(ema26, ema12));
                    if (lastDea == null) {
                        lastDea = e.getDif();
                    }
                    lastDea = computeUtils.getDEA(e.getDif(), lastDea);
                    e.setDea(lastDea);
                    BigDecimal macd = computeUtils.subs(e.getDif(), lastDea).multiply(new BigDecimal("2")).setScale(4, RoundingMode.HALF_UP);
                    e.setMacd(macd);
                } catch (Exception ee) {
                    System.out.println("error:" + e.getId());
                }
            }
            List<List<KlineUEntity>> listmaps = Lists.partition(list, 100);
            for (int i = 0; i < listmaps.size(); i++) {
                baseUMapper.updateListKlineUEntity(table, listmaps.get(i));
                System.out.println("success:" + i);

            }
        }
    }
}

