package org.example.testforiss.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.testforiss.entity.Model;
import org.example.testforiss.repository.DeviceRepository;
import org.example.testforiss.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author batal
 * @Date 01.02.2025
 */
@RestController
@RequestMapping("/api/models")
@Tag(name = "Model API", description = "Управление моделями техники")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    private final DeviceRepository deviceRepository;

    @Operation(
            summary = "Получить все модели",
            description = "Возвращает список всех моделей техники.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список моделей",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "[{ \"id\": \"aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa\", \"name\": \"Samsung 55Q80A\", \"serialNumber\": \"SN-TV-123\", \"color\": \"Черный\", \"size\": \"55 дюймов\", \"price\": 1200, \"availability\": true }]"
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public List<Model> getAllModels() {
        return modelService.getAllModels();
    }
    @Operation(
            summary = "Получить модель по ID",
            description = "Возвращает модель техники по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Модель найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": \"aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa\", \"name\": \"Samsung 55Q80A\", \"serialNumber\": \"SN-TV-123\", \"color\": \"Черный\", \"size\": \"55 дюймов\", \"price\": 1200, \"availability\": true }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Модель не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Модель с указанным ID не найдена\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable UUID id) {
        return modelService.getModelById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Получить модели конкретного устройства",
            description = "Возвращает список моделей, привязанных к указанному устройству",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Список моделей устройства",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "[{ \"id\": \"aaaaaaa1-aaaa-aaaa-aaaa-aaaaaaaaaaaa\", \"name\": \"Samsung 55Q80A\", \"serialNumber\": \"SN-TV-123\", \"color\": \"Черный\", \"size\": \"55 дюймов\", \"price\": 1200, \"availability\": true }]"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Устройство с указанным ID не найдено\" }"
                                    )
                            )
                    )
            }
    )
    @GetMapping("/device/{deviceId}")
    public ResponseEntity<?> getModelsByDevice(@PathVariable UUID deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Устройство с указанным ID не найдено"));
        }

        List<Model> models = modelService.getModelsByDevice(deviceId);
        return ResponseEntity.ok(models);
    }

    @Operation(
            summary = "Добавить модель",
            description = "Создает новую модель и привязывает её к устройству.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"name\": \"LG OLED C1\", \"serialNumber\": \"SN-TV-789\", \"color\": \"Серый\", \"size\": \"65 дюймов\", \"price\": 1400, \"availability\": true, \"deviceId\": \"11111111-1111-1111-1111-111111111111\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Модель успешно создана",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"id\": \"bbbbbbb1-bbbb-bbbb-bbbb-bbbbbbbbbbbb\", \"name\": \"LG OLED C1\", \"serialNumber\": \"SN-TV-789\", \"color\": \"Серый\", \"size\": \"65 дюймов\", \"price\": 1400, \"availability\": true, \"device\": { \"id\": \"11111111-1111-1111-1111-111111111111\", \"name\": \"Samsung QLED\" } }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Ошибка валидации запроса",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Модель с таким серийным номером уже существует.\" }"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Устройство не найдено",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{ \"error\": \"Устройство с указанным ID не найдено.\" }"
                                    )
                            )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createModel(@RequestBody Model model, @RequestParam UUID deviceId) {
        try {
            Model savedModel = modelService.saveModel(model, deviceId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    @Operation(
            summary = "Удалить модель",
            description = "Удаляет модель техники по её уникальному идентификатору",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Модель успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Модель не найдена",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{ \"error\": \"Модель с указанным ID не найдена\" }")
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable UUID id) {
        boolean deleted = modelService.deleteModel(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Модель с указанным ID не найдена"));
        }
        return ResponseEntity.noContent().build();
    }


}

