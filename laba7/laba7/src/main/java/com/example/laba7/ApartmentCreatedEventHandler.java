package com.example.laba7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "apartment-create-event-topic", groupId = "apartment-group-8082", containerFactory = "kafkaListenerContainerFactory")
public class ApartmentCreatedEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ApartmentRepository apartmentRepository;

    public ApartmentCreatedEventHandler(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @KafkaHandler
    public void handle(ApartmentCreateEvent apartmentCreateEvent) {
        LOGGER.info("Received ApartmentCreateEvent: {}", apartmentCreateEvent);
        int apartmentId = apartmentCreateEvent.getId().hashCode();
        LOGGER.info("Generated apartment ID (hash code): {}", apartmentId);

        if (apartmentRepository.existsById(apartmentId)) {
            LOGGER.info("ApartmentCreateEvent with ID {} already exists, skipping processing", apartmentCreateEvent.getId());
            return;
        }

        Apartment apartment = new Apartment();
        apartment.setId(apartmentId);
        apartment.setCity(apartmentCreateEvent.getCity());
        apartment.setPrice(apartmentCreateEvent.getPrice());
        apartment.setAddress(apartmentCreateEvent.getAddress());

        LOGGER.info("Saving Apartment with ID {}: {}", apartmentId, apartment);
        apartmentRepository.save(apartment);
        LOGGER.info("Apartment with ID {} successfully saved", apartment.getId());
    }
}
