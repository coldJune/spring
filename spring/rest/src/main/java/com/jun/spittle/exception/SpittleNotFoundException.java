package com.jun.spittle.exception;

public class SpittleNotFoundException extends   RuntimeException{
    private long spittleId;
    public SpittleNotFoundException(long spittleId){
        this.spittleId = spittleId;
    }

    public long getSpittleId() {
        return spittleId;
    }
}
