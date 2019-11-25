package com.coldjune.readinglistwithtest.controller;

import com.coldjune.readinglistwithtest.model.AmazonProperties;
import com.coldjune.readinglistwithtest.model.Book;
import com.coldjune.readinglistwithtest.service.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {
    private ReadingListRepository readingListRepository;
    private AmazonProperties amazonProperties;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties){
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
    }


    @RequestMapping( method = RequestMethod.GET)
    //处理/{reader}上的GET请求
    //从仓库获取book列表并塞入模型，用books作为键
    //返回readingList作为呈现模型的视图逻辑名称
    public String readerBooks( String reader, Model model){
        List<Book>  readinglistwithtest  = readingListRepository.findByReader(reader);
        if(readinglistwithtest != null){
            model.addAttribute("books", readinglistwithtest);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonId", amazonProperties.getAssociatedId());
        }
        return "readinglist";
    }

    @RequestMapping(method = RequestMethod.POST)
    //处理/{reader}上的POST请求
    //将请求正文的数据绑定到一个Book对象上，然后保存到仓库
    //最后重定向到/{reader}
    public String addToReadingList( String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }
}
