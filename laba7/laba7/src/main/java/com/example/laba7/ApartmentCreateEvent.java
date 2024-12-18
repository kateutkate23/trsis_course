package com.example.laba7;

public class ApartmentCreateEvent {
    private String id;

    private String city;

    private Integer price;

    private String address;

    public ApartmentCreateEvent() {
    }

    public ApartmentCreateEvent(String id, String city, Integer price, String address) {
        this.id = id;
        this.city = city;
        this.price = price;
        this.address = address;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
