package com.example.rich.service;

import com.example.rich.entity.u.KlineUEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface GetDataService {
    List<KlineUEntity> getAllByTime(String tableName, String symbol, Date startTime, Date endTime);
}
