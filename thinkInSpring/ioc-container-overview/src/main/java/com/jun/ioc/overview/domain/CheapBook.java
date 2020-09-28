package com.jun.ioc.overview.domain;

import com.jun.ioc.overview.annotation.Cheap;

/** 便宜书
 * @Author dengxiaojun
 * @Date 2020/7/26 19:01
 * @Version 1.0
 */
@Cheap
public class CheapBook extends Book{
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "CheapBook{" +
                "isbn='" + isbn + '\'' +
                "} " + super.toString();
    }
}
