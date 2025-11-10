package com.example.rich.enums;

public enum WeightEnum {
    zero(0),//最高优先级
    one(1),
    two(2),
    tree(3),
    four(4);


    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    private WeightEnum(Integer weight) {

        this.weight = weight;
    }

}
