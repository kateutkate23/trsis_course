package info.stepanoff.trsis.samples.rest.model;

import lombok.Getter;

@Getter
public class ApartmentDataWithoutID {
    private String city;
    private String address;
    private Integer price;

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPrice() {
        return price;
    }
}
