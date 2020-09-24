package com.jun.dependency.injection;

import com.jun.ioc.overview.dependency.domain.Book;

/**
 * {@linke Book} 的Holder类
 * @Author dengxiaojun
 * @Date 2020/9/24 10:48
 * @Version 1.0
 */
public class BookHolder {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookHolder(Book book) {
        this.book = book;
    }
    public BookHolder(){}

    @Override
    public String toString() {
        return "BookHolder{" +
                "book=" + book +
                '}';
    }
}
