# Stage 1: Build the application using Gradle
FROM gradle:8.2.1-jdk17 AS builder

WORKDIR /app

# Копируем файлы проекта в контейнер
COPY --chown=gradle:gradle . .

# Собираем проект
RUN gradle clean build -x test

# Stage 2: Создаем минимальный образ для запуска приложения
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем готовый jar из билдера
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]