package com.jun.spittle.controller;

import com.jun.spittle.error.MyError;
import com.jun.spittle.exception.SpittleNotFoundException;
import com.jun.spittle.model.Spittle;
import com.jun.spittle.repo.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import sun.security.provider.ConfigFile;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class SpittleRestController {

    @Autowired
    private SpittleRepository spittleRepository;
    @RequestMapping(value = "/exception/{id}", method = RequestMethod.GET)
    public Spittle spittleByIdWithException(@PathVariable long id){
        Optional<Spittle> optionalSpittle = spittleRepository.findById(id);
        Spittle spittle=null;
        try {
            spittle = optionalSpittle.get();
        }catch (NoSuchElementException e){
        }
        if(spittle == null){
            throw  new SpittleNotFoundException(id);
        }
        return spittle;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittle, UriComponentsBuilder uriComponentsBuilder){
        Spittle spittle1 = spittleRepository.save(spittle);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = uriComponentsBuilder.path("/spittles/")
                .path(String.valueOf(spittle1.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);
        ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(spittle, headers, HttpStatus.CREATED);
        return responseEntity;
    }
    @ExceptionHandler({SpittleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MyError spittleNotFound(SpittleNotFoundException e){
        long spittleId = e.getSpittleId();
        MyError error = new MyError(4,"spittle["+spittleId+"] not found");
        return error;
    }


}
