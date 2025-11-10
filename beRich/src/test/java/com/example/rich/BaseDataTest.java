package com.example.rich;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.rich.mapper.SymbolMapper;
import com.example.rich.mapper.u.KlineU1dMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
    调用接口，初始化数据
* */
@SpringBootTest
public class BaseDataTest {
    @Autowired
    private KlineU1dMapper klineU1dMapper;
    @Autowired
    private SymbolMapper symbolMapper;

    @Test
    public void test() throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        Integer i1 = klineU1dMapper.selectCount(queryWrapper);
        Integer i2 = klineU1dMapper.selectCount(queryWrapper);
        System.out.println(i1);
        System.out.println(i2);
    }
}
