package repository;

import entities.Apartment;

import java.util.List;
import java.util.ArrayList;

public class ApartmentRepository {
    private static final List<Apartment> apartments = new ArrayList<>();

    public static List<Apartment> getApartments() {
        return apartments;
    }

    public static void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public static void deleteApartment(int num) {
        if (num >= 0 && num < apartments.size()) {
            apartments.remove(num);
        }
    }
}
