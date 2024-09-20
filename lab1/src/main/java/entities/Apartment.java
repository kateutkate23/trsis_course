package entities;

public class Apartment {
    private String city;
    private String address;
    private String price;

    public Apartment(String city, String address, String price) {
        this.city = city;
        this.address = address;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }
}
