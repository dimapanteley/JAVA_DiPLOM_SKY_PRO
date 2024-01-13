package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.CommentServiceImpl;

@Tag(name = "Комментарии")
@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("ads")
public class CommentController {

    CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Получить комментарии объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comments.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @GetMapping("{id}/comments")
    public Comments getComments(@PathVariable int id) {
        log.info("Run method getComments in controller");
        return commentService.getComments(id);
    }

    @Operation(
            summary = "Добавить комментарий к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable int id, @RequestBody CreateOrUpdateComment comment,
                              Authentication authentication) {
        log.info("Run method addComment in controller");
        return commentService.addComment(id, comment, authentication);
    }

    @Operation(
            summary = "Удалить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize(value = "hasRole('ADMIN') " +
            "or @commentServiceImpl.isAuthorComment(authentication.getName(), #commentId)")
    public void deleteComment(@PathVariable int adId, @PathVariable int commentId) {
        log.info("Run method deleteComment in controller");
        commentService.deleteComment(commentId);
    }

    @Operation(
            summary = "Обновить комментарий",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Comment.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(schema = @Schema(hidden = true))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize(value = "hasRole('ADMIN') " +
            "or @commentServiceImpl.isAuthorComment(authentication.getName(), #commentId)")
    public Comment updateComment(
            @PathVariable int adId,
            @PathVariable int commentId,
            @RequestBody CreateOrUpdateComment updateComment) {
        log.info("Run method updateComment in controller");
        return commentService.updateComment(commentId, updateComment);
    }
}