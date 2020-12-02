package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;

/**
 * @Author dengxiaojun
 * @Date 2020/12/2 21:42
 * @Version 1.0
 */
public class BookHolder {
    private Book book;

    public BookHolder(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookHolder{" +
                "book=" + book +
                '}';
    }
}
