package com.example.rich.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorEntity {
    private String symbol;
    private Long id;
    private Date createTime;
    private String msg;
}
