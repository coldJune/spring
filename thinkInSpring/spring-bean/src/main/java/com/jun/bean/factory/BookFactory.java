package com.jun.bean.factory;

import com.jun.ioc.overview.dependency.domain.Book;

/**
 * {@link Book} 工厂类
 * @Author dengxiaojun
 * @Date 2020/8/29 21:09
 * @Version 1.0
 */
public interface BookFactory {
    default Book createBook(){
        return Book.createBook();
    }

}
