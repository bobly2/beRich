package com.example.rich.mapper.u;


import com.example.rich.entity.u.KlineUEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BaseUMapper {
//    List<>
    Integer getCountSymbol();

    List<KlineUEntity> getKlienUListForAdd(@Param("tableName") String tableName,@Param("symbol") String symbol);

    int insertListKlineUEntity(@Param("tableName") String tableName,@Param("list")  List<KlineUEntity> list);

    void deleteById(@Param("tableName") String tableName,@Param("id") Long id);

    int updateListKlineUEntity(@Param("tableName") String tableName,@Param("list")  List<KlineUEntity> list);

    List<KlineUEntity> getAllByTime(@Param("tableName") String tableName, @Param("symbol") String symbol, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<KlineUEntity> getFirstNoEma(@Param("tableName") String tableName, @Param("symbol") String symbol);
}
