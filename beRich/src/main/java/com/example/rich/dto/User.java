package com.example.rich.dto;

import lombok.Data;

/**
 * @Author: SC19002999
 * @Description:
 * @Date: 2021/12/30 18:36
 * @Version: 1.0
 */
@Data
public class User {
    private static final long serialVersionUID = -9180229310895087286L;
    private String name; // 姓名
    private String sex; // 性别
    private Integer age; // 年龄
    private String phoneNo; // 手机号
    private String address; // 地址
    private String hobby; // 爱好
    public User(String name, String sex, Integer age) {
        super();
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", sex=" + sex + ", age=" + age + ", phoneNo=" + phoneNo + ", address=" + address
                + ", hobby=" + hobby + "]";
    }

}
