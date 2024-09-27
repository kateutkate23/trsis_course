package org.openapitools.api;

import org.openapitools.model.Apartment;


import org.openapitools.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-27T18:19:31.380153700+03:00[Europe/Moscow]", comments = "Generator version: 7.8.0")
@Controller
@RequestMapping("${openapi.apartmentRentOpen.base-path:}")
public class AddApiController implements AddApi {

    private final NativeWebRequest request;
    private final ApartmentService apartmentService;

    @Autowired
    public AddApiController(NativeWebRequest request, ApartmentService apartmentService) {
        this.request = request;
        this.apartmentService = apartmentService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Apartment> addApartment(String city, String address, Integer price) {
        Apartment newApartment = apartmentService.addApartment(city, address, price);
        return new ResponseEntity<>(newApartment, HttpStatus.CREATED);
    }
}
