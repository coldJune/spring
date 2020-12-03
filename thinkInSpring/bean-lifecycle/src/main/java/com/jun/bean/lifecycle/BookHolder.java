package com.jun.bean.lifecycle;

import com.jun.ioc.overview.domain.Book;

/**
 * @Author dengxiaojun
 * @Date 2020/12/2 21:42
 * @Version 1.0
 */
public class BookHolder {
    private Book book;

    private Integer number;
    private String desc;
    public BookHolder(Book book) {
        this.book = book;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BookHolder{" +
                "book=" + book +
                ", number=" + number +
                ", desc='" + desc + '\'' +
                '}';
    }
}
