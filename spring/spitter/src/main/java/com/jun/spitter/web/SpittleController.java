package com.jun.spitter.web;

import com.jun.spitter.exception.DuplicateSpittleException;
import com.jun.spitter.exception.SpittleNotFoundException;
import com.jun.spitter.model.SpittleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.jun.spitter.model.Spittle;
import com.jun.spitter.repository.SpittleRepository;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SpittleController {

    private static  final String MAX_LONG_VALUE =  "9223372036854775807";
    private SpittleRepository spittleRepository;

//    @Autowired
//    public SpittleController(SpittleRepository spittleRepository){
//        this.spittleRepository = spittleRepository;
//    }
//    @RequestMapping(method = RequestMethod.GET)
//    public String spittles(Model model){
//        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//        model.addAttribute("spilleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//        return "spittles";
//    }
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Spittle> spittles(Model model){
//        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
//    }

    @RequestMapping(value = "/spittles", method = GET)
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue =MAX_LONG_VALUE) long max,
                                  @RequestParam(value = "count", defaultValue ="20") int count){
        if(max==0){
            throw new DuplicateSpittleException();
        }
        return spittleRepository.findSpittles(max,count);
    }

    @RequestMapping(value = "showSpittle/{spittleId}")
    public String showSpittle(@PathVariable("spittleId")long spittleId, Model model){
        try{
            Spittle spittle = spittleRepository.findOne(spittleId);
            if(spittle == null){
                throw  new SpittleNotFoundException();
            }
        }catch (Exception e){
            throw new SpittleNotFoundException();
        }

        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }

    @RequestMapping(method = POST)
    public String saveSpittle(SpittleForm form, Model model){
        spittleRepository.save(new Spittle(null,form.getMessage(),new Date(),form.getLongitude(),form.getLatitude()));
        return "redirect:/spittles";
    }

    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleDuplicateSpittle(){
        return "error/duplicate";
    }
}
