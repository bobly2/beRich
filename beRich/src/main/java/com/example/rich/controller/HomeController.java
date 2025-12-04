package com.example.rich.controller;

import com.example.rich.service.BaseDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/2 9:47
 * @Version: 1.0
 */

@RestController
@Slf4j
@RequestMapping("/api")
public class HomeController {
    @Autowired(required = true)
    private BaseDataService baseDataService;
    @GetMapping("/test")
    @CrossOrigin
    public String test() {
        return "success";
    }
    @GetMapping("/init")
    @CrossOrigin
    public String initData() {
        baseDataService.initKlineUList("ZECUSDT");
        return "success";
    }

}
