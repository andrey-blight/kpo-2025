# Сервис для обработки файлов

Сервис состоит из трех микросервисов

1) Gateway - главная задача перенаправлять запросы. Сервис работает по адресу localhost:8080. В случае, если микросервис
   не отвечает, возвращает 502 Bad Gateway.

   Доступные методы:
    1) POST /upload -> storage /upload.
    2) GET /content/{id} -> storage /content/{id}
    3) GET /statistics/{id} -> analytics /statistics/{id}
    4) GET /word_cloud/{id} -> analytics /word_cloud/{id}

2) Storage - сервис для хранения файлов и проверки на дубликаты. Сервис работает по адресу localhost:8081

   Доступные методы:
    1) POST upload -> Возвращает содержимое файла.
    2) GET /content/{id} -> Берет файл из файлового хранилища и возвращает текст, который в нем содержится.

3) Analytics - сервис для подсчета статистики текста файлов, также ходит в сторонний сервис для генерации облаков слов.
   Важно, что сервис облака слов поддерживает только файлы на английском языке. Сервис работает по адресу localhost:8082

   Доступные методы:
    1) GET /statistics/{id} -> Возвращает статистику по файлу. А именно кол-во слов и кол-во букв. Также генерирует
       картинку облака слов и сохраняет его в файловое хранилище.
    2) GET /word_cloud/{id} -> Возвращает svg картинку с облаком слов.

Для каждого сервиса хранится swagger по адресу:

1) Gateway: http://localhost:8080/swagger-ui.html
2) Storage: http://localhost:8081/swagger-ui.html
3) Analytics: http://localhost:8082/swagger-ui.html

## Архитектура

1) В каждом сервисе есть свой build.gradle, так что сервисы это полноценные модули.
2) База данных поднята в docker-compose
3) В пакете controller содержатся ручки сервиса
4) В пакете entity содержится описание сущностей базы данных
5) В пакете repository хранится репозиторий для взаимодействия с базой данных
6) В пакете Service хранится сервис для реализации бизнес логики

Запуск происходит путем старта docker-compose и поднятия всех трех микросервисов.