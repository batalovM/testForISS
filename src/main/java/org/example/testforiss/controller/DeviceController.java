package org.example.testforiss.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.testforiss.entity.Device;
import org.example.testforiss.entity.DeviceType;
import org.example.testforiss.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device API", description = "Управление устройствами")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "Получить все устройства")
    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }
    @Operation(
            summary = "Получить устройство по ID",
            description = "Возвращает устройство по его уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Устройство найдено",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": \"11111111-1111-1111-1111-111111111111\", \"name\": \"Samsung QLED\", \"manufacturerCountry\": \"Корея\", \"manufacturerCompany\": \"Samsung\", \"onlineOrderAvailable\": true, \"installmentAvailable\": true, \"type\": \"TV\", \"models\": [] }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Устройство с таким ID не найдено\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(
            @Parameter(description = "UUID устройства") @PathVariable UUID id) {
        return deviceService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Поиск устройств по названию",
            description = "Ищет устройства, у которых в названии содержится указанная строка (без учета регистра)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список найденных устройств",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "[{ \"id\": \"11111111-1111-1111-1111-111111111111\", \"name\": \"Samsung QLED\", \"manufacturerCountry\": \"Корея\", \"manufacturerCompany\": \"Samsung\", \"onlineOrderAvailable\": true, \"installmentAvailable\": true, \"type\": \"TV\", \"models\": [] }]"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/search")
    public List<Device> searchDevices(@RequestParam String name) {
        return deviceService.searchDevicesByName(name);
    }
    @Operation(
            summary = "Фильтрация устройств",
            description = "Фильтрует устройства по типу, цвету и диапазону цен (от/до). Все параметры необязательны.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список отфильтрованных устройств",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "[{ \"id\": \"11111111-1111-1111-1111-111111111111\", \"name\": \"Samsung QLED\", \"manufacturerCountry\": \"Корея\", \"manufacturerCompany\": \"Samsung\", \"onlineOrderAvailable\": true, \"installmentAvailable\": true, \"type\": \"TV\", \"models\": [ { \"id\": \"aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa\", \"name\": \"Samsung 55Q80A\", \"serialNumber\": \"SN-TV-123\", \"color\": \"Черный\", \"size\": \"55 дюймов\", \"price\": 1200, \"availability\": true, \"category\": \"Premium\", \"technology\": \"QLED\" } ] }]"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/filter")
    public List<Device> filterDevices(
            @RequestParam(required = false) DeviceType type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice) {
        return deviceService.filterDevices(type, color, minPrice, maxPrice);
    }
    @Operation(
            summary = "Сортировка устройств",
            description = "Сортирует устройства по наименованию (по алфавиту) или по цене (по возрастанию).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список отсортированных устройств"),
                    @ApiResponse(responseCode = "400", description = "Некорректный параметр sortBy")
            }
    )
    @GetMapping("/sort")
    public ResponseEntity<?> sortDevices(@RequestParam String sortBy) {
        if (!"name".equalsIgnoreCase(sortBy) && !"price".equalsIgnoreCase(sortBy)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Некорректный параметр sortBy. Доступные значения: name, price."));
        }

        List<Device> sortedDevices = deviceService.getSortedDevices(sortBy);
        return ResponseEntity.ok(sortedDevices);
    }
    @Operation(
            summary = "Добавить устройство",
            description = "Создает новое устройство без моделей.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"name\": \"Samsung QLED\", \"manufacturerCountry\": \"Корея\", \"manufacturerCompany\": \"Samsung\", \"onlineOrderAvailable\": true, \"installmentAvailable\": true, \"type\": \"TV\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Устройство успешно создано"),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации запроса")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createDevice(@RequestBody Device device) {
        if (device.getName() == null || device.getManufacturerCountry() == null ||
                device.getManufacturerCompany() == null || device.getType() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Заполните все обязательные поля: name, manufacturerCountry, manufacturerCompany, type."));
        }

        Device savedDevice = deviceService.saveDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
    }
    @Operation(
            summary = "Удалить устройство",
            description = "Удаляет устройство по ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Устройство успешно удалено"),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        boolean deleted = deviceService.deleteDevice(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }




}
