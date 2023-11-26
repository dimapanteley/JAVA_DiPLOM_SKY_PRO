package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Operation(summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Password.class)
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "403", description = "Forbidden",
                            content = @Content(schema = @Schema(hidden = true))),

            }
    )
    @PostMapping("/set_password")//Обновление пароля
    public ResponseEntity<Password> setPasword() {
        return ResponseEntity.ok(new Password());
    }

    @Operation(summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))),

            }
    )
    @GetMapping("/me")//Получение информации об авторизованном пользователе
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserUpdateInfo.class)
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))),

            }
    )
    @PatchMapping("/me")//Обновление информации об авторизованном пользователе
    public ResponseEntity<UserUpdateInfo> updateUser() {
        return ResponseEntity.ok(new UserUpdateInfo());
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя",
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
            }
    )
    @PatchMapping(path = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateAvatar( @RequestPart MultipartFile image) {
        byte[] avatar = {};
        return ResponseEntity.ok(avatar);
    }
}
