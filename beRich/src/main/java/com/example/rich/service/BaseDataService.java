package com.example.rich.service;

import com.example.rich.entity.u.KlineUEntity;

import java.util.List;

public interface BaseDataService {
    void initKlineUList(String symbol);

    void updateKlineUList(String symbol);

}
