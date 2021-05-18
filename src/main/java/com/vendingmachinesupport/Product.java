package com.vendingmachinesupport;

public enum Product {
    Coke("Coke", 25),
    Pepsi("Pepsi", 35),
    Soda("Soda", 45);

    private String productName;

    private long price;

    Product(String productName, long price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public long getPrice() {
        return price;
    }
}
