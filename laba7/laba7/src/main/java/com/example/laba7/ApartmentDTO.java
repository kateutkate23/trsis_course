package com.example.laba7;

public class ApartmentDTO {
    private String city;

    private Integer price;

    private String address;

    public ApartmentDTO(String city, Integer price, String address) {
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
}
