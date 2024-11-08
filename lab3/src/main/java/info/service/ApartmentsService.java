package info.service;

import info.rest.model.ApartmentsDTO;

import java.util.List;

public interface ApartmentsService {

    List<ApartmentsDTO> listAll();

    void delete(Integer ID);

    ApartmentsDTO add(String city, String address, Integer price);

    ApartmentsDTO findByID(Integer ID);

}