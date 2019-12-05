package com.jun.spitter.web;

import com.jun.spitter.model.Spitter;
import com.jun.spitter.repository.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

//    @Autowired
    public SpitterController(SpitterRepository spitterRepository){
        this.spitterRepository = spitterRepository;
    }

    public SpitterController(){

    }
    @RequestMapping(value = "/showRegister", method = GET)
    public String showRegistrationForm(Model model){
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(Spitter spitter, RedirectAttributes model){
//        spitterRepository.save(spitter);
        model.addAttribute("username",spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/registerWithValid", method = POST)
    public String processRegistrationWithValid(@Valid Spitter spitter, Errors errors){
        if(errors.hasErrors()){
            return "registerForm";
        }
        spitterRepository.save(spitter);
        return "redirect:/spitter/"+spitter.getUsername();
    }

    @RequestMapping("/{username}")
    public String showSpitter(@PathVariable String username, Model model){
        if(!model.containsAttribute("spitter")){
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute(spitter);
        }
        return "profile";
    }

    @RequestMapping(value = "me", method = GET)
    public String me(){
        return "home";
    }
}
