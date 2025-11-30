package com.example.rich.service;

import com.example.rich.entity.u.KlineUEntity;

import java.util.List;

public interface BaseDataService {
    void initKlineUList(String symbol);

    void updateKlineUList(String table, List<KlineUEntity> list);

    void updateEma2060(String symbol,String table);
}
