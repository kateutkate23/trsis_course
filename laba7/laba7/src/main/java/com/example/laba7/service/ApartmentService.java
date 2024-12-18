package com.example.laba7.service;

import com.example.laba7.ApartmentDTO;

import java.util.concurrent.ExecutionException;

public interface ApartmentService {
    String createApartment(ApartmentDTO apartmentDTO) throws ExecutionException, InterruptedException;
}
