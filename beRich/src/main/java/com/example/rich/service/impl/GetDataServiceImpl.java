package com.example.rich.service.impl;

import com.example.rich.entity.u.KlineUEntity;
import com.example.rich.enums.TableNameEnum;
import com.example.rich.mapper.u.BaseUMapper;
import com.example.rich.service.GetDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class GetDataServiceImpl implements GetDataService {
    @Autowired
    private BaseUMapper baseUMapper;
    @Override
    public List<KlineUEntity> getAllByTime(String tableName, String symbol, Date startTime, Date endTime) {
        List<KlineUEntity> list =  baseUMapper.getAllByTime(tableName,symbol,null,null);
        return list;
    }



}
