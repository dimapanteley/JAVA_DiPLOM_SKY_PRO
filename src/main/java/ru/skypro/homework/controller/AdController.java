package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@Tag(name = "Объявления")
@RestController
@RequestMapping("ads")
public class AdController {

    @Operation(summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ads.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(new Ads());
    }

    @Operation(summary = "Добавление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ad.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@ModelAttribute AddingAd ad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new Ad());
    }

    @Operation(summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ExtendedAd.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable int id) {
        return ResponseEntity.ok(new ExtendedAd());
    }

    @Operation(summary = "Удаление объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> removeAd(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Обновление информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ad.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PatchMapping("{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable int id, @RequestBody CreateOrUpdateAd ad) {
        return ResponseEntity.ok(new Ad());
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ads.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping("me")
    public ResponseEntity<Ads> getAdsMe() {
        return ResponseEntity.ok(new Ads());
    }

    @Operation(summary = "Обновление картинки объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/octet-stream",
                                    array = @ArraySchema(schema = @Schema(implementation = String.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PatchMapping(path = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable int id, @RequestPart MultipartFile image) {
        byte[] array = {};
        return ResponseEntity.ok(array);
    }
}