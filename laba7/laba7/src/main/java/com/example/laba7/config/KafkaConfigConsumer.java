package com.example.laba7.config;

import com.example.laba7.ApartmentCreateEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfigConsumer {

    private final Environment environment;

    @Autowired
    public KafkaConfigConsumer(Environment environment) {
        this.environment = environment;
    }

    // Конфигурация ConsumerFactory для чтения из Kafka
    @Bean
    public ConsumerFactory<String, ApartmentCreateEvent> consumerFactory() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.consumer.bootstrap-servers"));
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "apartment-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // Используем JsonDeserializer для десериализации объекта ApartmentCreateEvent
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);  // Передаем класс, а не объект

        // Указываем trusted пакеты для JsonDeserializer, чтобы разрешить десериализацию классов
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ApartmentCreateEvent.class);

        return new DefaultKafkaConsumerFactory<>(consumerProps);
    }


    // Конфигурация KafkaListenerContainerFactory
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ApartmentCreateEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, ApartmentCreateEvent> consumerFactory) {

        // Конфигурируем контейнер слушателя сообщений
        ConcurrentKafkaListenerContainerFactory<String, ApartmentCreateEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
