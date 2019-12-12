package com.jun.spittle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Spittle {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private Date created_at;
    private Double latitude;
    private Double longitude;

    public Spittle() {}

    public Spittle(Long id, String message, Date time, Double longitude, Double latitude) {
        this.id = id;
        this.message = message;
        this.created_at = time;
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
        return created_at;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
