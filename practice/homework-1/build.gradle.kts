plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.springframework.boot") version "3.2.2"  // Подключаем Spring Boot
    id("io.spring.dependency-management") version "1.1.4" // Управление зависимостями Spring
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Test (без версии!)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter")

    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Lombok (для основного кода и аннотаций)
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Spring Context (без версии, так как Spring Boot сам управляет)
    implementation("org.springframework:spring-context")

    // Для тестов
    testImplementation("org.mockito:mockito-core")
}

tasks.test {
    useJUnitPlatform()
}