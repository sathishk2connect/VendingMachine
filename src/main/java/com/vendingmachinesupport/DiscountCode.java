package com.vendingmachinesupport;

public enum DiscountCode {
    CODE1(-1),CODE2(-5),CODE3(-10);

    private long discount;

    DiscountCode(long discount) {
        this.discount = discount;
    }

    public long getDiscount() {
        return discount;
    }
}
