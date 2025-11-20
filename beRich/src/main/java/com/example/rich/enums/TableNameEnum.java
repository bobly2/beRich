package com.example.rich.enums;

public enum TableNameEnum {
    d1("d1","kline_u_d1"),
    m5("m5","kline_u_m5"),
    h1("h1","kline_u_h1"),
    w1("1w","1w"),
    month1("1M","1M");

    private String tableName;
    private String tableType;
    TableNameEnum(String tableType, String tableName) {
        this.tableType = tableType;
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
