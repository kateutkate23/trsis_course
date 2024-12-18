package com.example.laba7;

import com.example.laba7.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping
    public ResponseEntity<String> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        LOGGER.info("Received request to create apartment: {}", apartmentDTO);
        String apartmentId = null;
        try {
            apartmentId = apartmentService.createApartment(apartmentDTO);
            LOGGER.info("Apartment created successfully with ID: {}", apartmentId);
        } catch (Exception e) {
            LOGGER.error("Error occurred while creating apartment: {}", e.getMessage());
            throw new RuntimeException("Failed to create apartment", e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(apartmentId);
    }
}
