package com.jun.ioc.overview.domain;

import com.jun.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

/**
 * @Author dengxiaojun
 * @Date 2020/7/26 18:42
 * @Version 1.0
 */
public class Book {
    private int price;
    private String name;

    private City city;
    private City[] saleCities;
    private List<City> publishCities;
    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Book setPrice(int price) {
        this.price = price;
        return this;
    }

    private Resource resource;
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getSaleCities() {
        return saleCities;
    }

    public void setSaleCities(City[] saleCities) {
        this.saleCities = saleCities;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<City> getPublishCities() {
        return publishCities;
    }


    public void setPublishCities(List<City> publishCities) {
        this.publishCities = publishCities;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", saleCities=" + Arrays.toString(saleCities) +
                ", publishCities=" + publishCities +
                ", resource=" + resource +
                '}';
    }

    public static Book createBook(){
        Book book = new Book();
        book.setName("staticMethodBook")
                .setPrice(1);
        return book;
    }
}
