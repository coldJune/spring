package com.jun.bean.scope.web.controller;

import com.jun.ioc.overview.domain.Book;
import com.jun.ioc.overview.domain.CheapBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器 Spring Web Mvc Controller
 * @Author dengxiaojun
 * @Date 2020/11/15 20:04
 * @Version 1.0
 */

@Controller
public class IndexController {

    @Autowired
    private Book book; //CGLIB提升，通常不变

    @Autowired
    private CheapBook cheapBook; //CGLIB提升，通常不变

    @GetMapping("index.html")
    public String index(Model model){
        model.addAttribute("book",book);
        model.addAttribute("cheapBook",cheapBook);
        return "index";
    }
}
