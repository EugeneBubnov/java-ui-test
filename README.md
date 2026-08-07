# Демо ui-тестов под 2-х уровневую архитектуру

[![Java](https://img.shields.io/badge/Java-25-blue)](https://www.oracle.com/java/)
[![JUnit](https://img.shields.io/badge/JUnit-5.10%2B-green)](https://junit.org/junit5/)
[![Selenide](https://img.shields.io/badge/Selenide-7.0%2B-orange)](https://selenide.org/)
[![Allure](https://img.shields.io/badge/Allure%20Report-2.24%2B-ff69b4)](https://allurereport.org/)

Проект демонстрирует подход разработки ui-тестов:
* Уровни тестирования: UI/E2E
* Техника тест-дизайна: Use Case
* Паттерн проектирования: Page Object
* Реализован E2E-сценарий покупки товара
* Allure отчёт с детальными шагами

## Технологический стек
- **Java 25** — Язык программирования
- **Maven** — Сборщик
- **JUnit 5** — Тест-раннер и фреймворк для написания тестов
- **Selenide** — Библиотека для UI-тестов
- **Allure** — Отчётность
- **JavaFaker** — Генерация тестовых данных

## Запуск тестов
```bash
# Запуск тестов
mvn clean test

# Генерация и открытие Allure отчета
allure serve target/allure-results
```

## Отчёт
![allure](src/main/resources/allure.png)
