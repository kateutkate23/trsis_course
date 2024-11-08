package org.openapitools.services;

import org.openapitools.model.Apartment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentService {
    private final List<Apartment> apartments = new ArrayList<>();

    public List<Apartment> getAllApartments() {
        return apartments;
    }

    public Apartment addApartment(String city, String address, Integer price) {
        Apartment newApartment = new Apartment(city, address, price);
        apartments.add(newApartment);
        return newApartment;
    }

    public Apartment deleteApartment(int id) {
        if (id >= 0 && id < apartments.size()) {
            return apartments.remove(id);
        }
        return null;
    }
}
