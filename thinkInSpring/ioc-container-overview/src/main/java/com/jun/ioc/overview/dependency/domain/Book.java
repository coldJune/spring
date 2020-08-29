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

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Book setPrice(int price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", name=" + name +
                '}';
    }

    public static Book createBook(){
        Book book = new Book();
        book.setName("staticMethodBook")
                .setPrice(1);
        return book;
    }
}
