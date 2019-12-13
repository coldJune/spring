package com.jun.message.domain;

import java.util.Date;

public class Spittle {
    private Long id;
    private Spitter spitter;
    private String message;
    private Date postedTime;

    public Spittle(){

    }
    public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.postedTime = postedTime;
    }

    public Long getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getPostedTime() {
        return this.postedTime;
    }

    public Spitter getSpitter() {
        return this.spitter;
    }

    @Override
    public String toString() {
        return spitter.getFullName()+":"+getMessage();
    }
}
