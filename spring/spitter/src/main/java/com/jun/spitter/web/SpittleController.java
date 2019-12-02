package com.jun.spitter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jun.spitter.model.Spittle;
import com.jun.spitter.repository.SpittleRepository;

import java.util.List;

//@Controller
//@RequestMapping("/spittles")
//public class SpittleController {
//
//    private SpittleRepository spittleRepository;
//
//    @Autowired
//    public SpittleController(SpittleRepository spittleRepository){
//        this.spittleRepository = spittleRepository;
//    }
////    @RequestMapping(method = RequestMethod.GET)
////    public String spitters(Model model){
////        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
////        model.addAttribute("spilleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
////        return "spittles";
////    }
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Spittle> spitters(Model model){
//        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
//    }
//}
