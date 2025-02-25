# Реестр техники (REST API)

### Описание проекта
REST API для управления реестром техники (телевизоры, пылесосы, холодильники, смартфоны и ПК). API поддерживает CRUD-операции, поиск, фильтрацию и сортировку.

---

## Технологии
- **Java 8**
- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Swagger (Springdoc OpenAPI v3)**
- **Maven**

---

## Как запустить проект?

### 1️ Установить зависимости
Перед запуском убедитесь, что у вас установлены:
- **JDK 8+**
- **Maven**
- **PostgreSQL**

### 2️ Настроить базу данных
Создайте базу данных в PostgreSQL:
```sql
CREATE DATABASE some_name;
```
#### И заполните файл с помощью скрипта data.sql 
Настройте подключение в `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/some_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3️ Запуск на Windows
1. Откройте **Командную строку (cmd)** или **PowerShell**.
2. Перейдите в папку с проектом:
   ```sh
   cd C:\путь_к_проекту
   ```
3. Соберите и запустите проект:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. После успешного запуска API будет доступно по адресу:
   ```
   http://localhost:8080
   ```

### 4️ Запуск на Linux / macOS
1. Откройте терминал и перейдите в папку с проектом:
   ```sh
   cd /path/to/project
   ```
2. Соберите и запустите проект:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

---

##  Документация API (Swagger UI)
После запуска перейдите по адресу:
🔗 [Swagger UI](http://localhost:8080/swagger-ui.html)

---

##  Основные эндпоинты

###  **Device API (Реестр техники)**
| Метод    | Эндпоинт                                                     | Описание                  |
|----------|--------------------------------------------------------------|---------------------------|
| `GET`    | `/api/devices`                                               | Получить все устройства   |
| `GET`    | `/api/devices/{id}`                                          | Получить устройство по ID |
| `GET`    | `/api/devices/search?name=...`                               | Поиск по названию         |
| `GET`    | `/api/devices/filter?type=...&color=...&minPrice=...&maxPrice=...` | Фильтрация                |
| `GET`    | `/api/devices/sort?sortBy=name`                              | Сортировка                |
| `POST`   | `/api/devices`                                               | Добавить устройство       |
| `DELETE` | `/api/devices/{id}`                                          | Удалить устройство        |

###  **Model API (Модели техники)**
| Метод | Эндпоинт | Описание |
|--------|------------------|--------------------------------|
| `GET` | `/api/models` | Получить все модели |
| `GET` | `/api/models/{id}` | Получить модель по ID |
| `GET` | `/api/models/device/{deviceId}` | Получить модели устройства |
| `POST` | `/api/models?deviceId=...` | Добавить модель |
| `DELETE` | `/api/models/{id}` | Удалить модель |

---

##  Примеры запросов

### 1️1 **Добавить устройство**
```http
POST /api/devices
Content-Type: application/json
```
```json
{
  "name": "Samsung QLED",
  "manufacturerCountry": "Корея",
  "manufacturerCompany": "Samsung",
  "onlineOrderAvailable": true,
  "installmentAvailable": true,
  "type": "TV"
}
```

### 2️ **Добавить модель**
```http
POST /api/models?deviceId=11111111-1111-1111-1111-111111111111
Content-Type: application/json
```
```json
{
  "name": "Samsung 55Q80A",
  "serialNumber": "SN-TV-123",
  "color": "Черный",
  "size": "55 дюймов",
  "price": 1200,
  "availability": true
}
```

---


