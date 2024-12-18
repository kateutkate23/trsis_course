# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл JAR в контейнер
COPY target/lab4SecureWebapp.jar app.jar

# Копируем keystore.p12 в контейнер
COPY target/keystore.p12 /app/target/keystore.p12

# Открываем порт, на котором будет работать приложение
EXPOSE 8443

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"] 
