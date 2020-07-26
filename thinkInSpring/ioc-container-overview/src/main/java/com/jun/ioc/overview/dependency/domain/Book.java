package com.jun.ioc.overview.dependency.domain;

/**
 * @Author dengxiaojun
 * @Date 2020/7/26 18:42
 * @Version 1.0
 */
public class Book {
    private int price;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", name=" + name +
                '}';
    }
}
