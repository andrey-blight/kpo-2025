# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- Что такое Docker? зачем он нужен?
Докер это инструмент, который делает контейнеризацию проекта и позволяет запускать их изолированно на любом устройстве
- Как поднять бд в докере?
Для того чтобы поднять бд в докере нужно в docker-compose прописать импорт образа нужно бд и в env указать конфиг
по которому нужно подключаться. Вот пример
```yaml
services:
  postgres:
    image: postgres:15-alpine
    container_name: postgres_db
    environment:
      POSTGRES_DB: car_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d postgres"]
      interval: 5s
      timeout: 5s
      retries: 5

volumes:
  postgres_data:
```
- Как подключить бд к приложению?
Для того чтобы подключить бд к приложению нужно в application.yaml указать конфиг и креды к базе данных
```yaml
spring:
  application:
    name: kpo-app
  datasource:
    url: jdbc:postgresql://localhost:5432/car_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
server:
  port: 8080
```
- Что такое Repository?
Репозиторий это место куда можно сохранить образ и потом его подтянуть. То есть я могу собрать контейнер локально,
запушить его в Docker HUB и на сервере просто спулить контейнер без билда.
- +1 уникальный факт связанный с темами выше или семинаром
Докер изначально как и гит был внутренней корпоративной разработкой, но потом перешел в open source и завоевал 
- популярность