package com.example.rich.mapper.u;


import com.example.rich.entity.u.KlineUEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface BaseUMapper {
//    List<>
    Integer getCountSymbol();

    List<KlineUEntity> getKlienUList(@Param("tableName") String tableName,@Param("symbol") String symbol);

    int insertListKlineUEntity(@Param("tableName") String tableName,@Param("list")  List<KlineUEntity> list);

    void deleteById(@Param("tableName") String tableName,@Param("id") Long id);

    int updateListKlineUEntity(@Param("tableName") String tableName,@Param("list")  List<KlineUEntity> list,String symbol);
}
