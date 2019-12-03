package com.jun.spitter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    @RequestMapping(method = GET)
    public String uploadForm(){
        return "uploadForm";
    }

    @RequestMapping(method = POST)
    public String processUpload(@RequestPart("file")MultipartFile file){
        System.out.println(file.getSize());
        return "redirect:/";
    }

}
