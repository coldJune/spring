package com.jun.spittle.model;

import java.util.Date;

public class Spittle {
    private Long id;
    private String message;
    private Date time;
    private Double latitude;
    private Double longitude;

    public Spittle() {}

    public Spittle(Long id, String message, Date time, Double longitude, Double latitude) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
