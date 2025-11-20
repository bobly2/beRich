package com.example.rich.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/8 11:41
 * @Version: 1.0
 */
@Data
public class BaseEntity {
    private Long id;
    private Date createTime;
}
