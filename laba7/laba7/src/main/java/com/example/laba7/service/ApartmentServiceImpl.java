package com.example.laba7.service;

import com.example.laba7.ApartmentCreateEvent;
import com.example.laba7.ApartmentDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private KafkaTemplate<String, ApartmentCreateEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ApartmentServiceImpl(@Qualifier("kafkaTemplateProducer") KafkaTemplate<String, ApartmentCreateEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createApartment(ApartmentDTO apartmentDTO) throws ExecutionException, InterruptedException {
        String apartmentId = UUID.randomUUID().toString();
        LOGGER.info("Creating apartment with ID: {}", apartmentId);

        ApartmentCreateEvent apartmentCreateEvent = new ApartmentCreateEvent(apartmentId, apartmentDTO.getCity(), apartmentDTO.getPrice(), apartmentDTO.getAddress());

        ProducerRecord<String, ApartmentCreateEvent> record = new ProducerRecord<>("apartment-create-event-topic", apartmentId, apartmentCreateEvent);

        SendResult<String, ApartmentCreateEvent> result = kafkaTemplate.send(record).get();

        LOGGER.info("Message sent to Kafka topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());
        LOGGER.info("Apartment creation event sent with ID: {}", apartmentId);

        return apartmentId;
    }
}
