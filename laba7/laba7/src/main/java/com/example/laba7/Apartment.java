package com.example.laba7;

import jakarta.persistence.*;

@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Автоинкремент для ID
    private Integer id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "price")
    private Integer price;

    @Column(name = "address")
    private String address;


    public Apartment(String city, Integer price, String address) {
        this.city = city;
        this.price = price;
        this.address = address;
    }

    public Apartment() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
