Запустите Kafka через Docker:

Перейдите в директорию, где находится файл docker-compose.yml.
Выполните команду:
docker-compose up -d

Войдите в контейнер Kafka:
docker exec -it laba7-kafka-3-1 bash

Создайте топик:
kafka-topics.sh --create \
  --bootstrap-server kafka-1:9092 \
  --replication-factor 3 \
  --partitions 3 \
  --topic apartment-create-event-topic \
  --config min.insync.replicas=2

Настройка и запуск двух экземпляров приложения:
В IntelliJ IDEA укажите порт для каждого экземпляра через VM Options:
Для первого экземпляра:
-Dserver.port=8080
Для второго экземпляра:
-Dserver.port=8082

Настройте базу данных H2 в файле application.properties для каждого экземпляра:
Первый экземпляр:
spring.datasource.url=jdbc:h2:file:C:/data/test;AUTO_SERVER=TRUE
Kafka Listener:
@KafkaListener(topics = "apartment-create-event-topic", groupId = "apartment-group-8080", containerFactory = "kafkaListenerContainerFactory")

Второй экземпляр:
spring.datasource.url=jdbc:h2:file:C:/data/test2;AUTO_SERVER=TRUE
Kafka Listener:
@KafkaListener(topics = "apartment-create-event-topic", groupId = "apartment-group-8082", containerFactory = "kafkaListenerContainerFactory")

Запустите оба экземпляра приложения:
Сначала запустите первый экземпляр на порту 8080.
Затем запустите второй экземпляр на порту 8082.

Проверка работы:
Войдите в консоль H2 (доступна по адресу http://localhost:8080/h2-console или http://localhost:8082/h2-console).
Отправьте запрос через Postman на один из экземпляров:

http://localhost:8080/apartment (или http://localhost:8082/apartment)
Убедитесь, что оба экземпляра обработали событие и данные синхронизированы в бд.