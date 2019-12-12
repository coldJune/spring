package com.jun.spittle.controller;

import com.jun.spittle.error.MyError;
import com.jun.spittle.exception.SpittleNotFoundException;
import com.jun.spittle.model.Spittle;
import com.jun.spittle.repo.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
    @Autowired
    private SpittleRepository spittleRepository;
    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    @RequestMapping(method = RequestMethod.GET,
    produces = "application/json")
    public @ResponseBody List<Spittle> spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING )long max,
                           @RequestParam(value = "count", defaultValue = "20") int count){
        return spittleRepository.findSpittles(max, count);
    }

    @RequestMapping(method = RequestMethod.POST,
    consumes = "application/json")
    public @ResponseBody Spittle saveSpittle(@RequestBody Spittle spittle){

        return spittleRepository.save(spittle);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Spittle> spittleById(@PathVariable long id){
        Optional<Spittle> optionalSpittle = spittleRepository.findById(id);
        Spittle spittle=null;
        try {
            spittle = optionalSpittle.get();
        }catch (NoSuchElementException e){
        }
        HttpStatus status = spittle !=null?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<Spittle>(spittle, status);
    }

    @RequestMapping(value = "/error/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> spittleByIdWithError(@PathVariable long id){
        Optional<Spittle> optionalSpittle = spittleRepository.findById(id);
        Spittle spittle=null;
        try {
            spittle = optionalSpittle.get();
        }catch (NoSuchElementException e){
        }
        if(spittle == null){
            MyError error = new MyError(4,"spittle["+id+"] not found");
            return new ResponseEntity<MyError>(error,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
    }

    @RequestMapping(value = "/exception/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> spittleByIdWithException(@PathVariable long id){
        Optional<Spittle> optionalSpittle = spittleRepository.findById(id);
        Spittle spittle=null;
        try {
            spittle = optionalSpittle.get();
        }catch (NoSuchElementException e){
        }
        if(spittle == null){
          throw  new SpittleNotFoundException(id);
        }
        return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    public ResponseEntity<MyError> spittleNotFound(SpittleNotFoundException e){
        long spittleId = e.getSpittleId();
        MyError error = new MyError(4,"spittle["+spittleId+"] not found");
        return new ResponseEntity<MyError>(error,HttpStatus.NOT_FOUND);
    }
}
