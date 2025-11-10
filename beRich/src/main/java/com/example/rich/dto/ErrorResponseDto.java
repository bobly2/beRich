package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/2 10:41
 * @Version: 1.0
 */
@Data
public class ErrorResponseDto {
    private Integer code;
    private String msg;
}
