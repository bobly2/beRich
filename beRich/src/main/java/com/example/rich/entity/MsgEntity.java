package com.example.rich.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MsgEntity {
    private Long id;
    private String msg;
    private Date createTime;
}
