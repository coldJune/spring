package com.coldjune.readinglist.controller;

import com.coldjune.readinglist.model.Book;
import com.coldjune.readinglist.service.ReadingListRepository;
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
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    private String associateId;
    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    public void setAssociateId(String associateId){
        this.associateId=associateId;
    }
    @RequestMapping( method = RequestMethod.GET)
    //处理/{reader}上的GET请求
    //从仓库获取book列表并塞入模型，用books作为键
    //返回readingList作为呈现模型的视图逻辑名称
    public String readerBooks(@PathVariable("reader") String reader, Model model){
        List<Book>  readingList  = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonId", associateId);
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    //处理/{reader}上的POST请求
    //将请求正文的数据绑定到一个Book对象上，然后保存到仓库
    //最后重定向到/{reader}
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }
}
