package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.model.CommentEntity;

public class CommentMapper {
    public static Comment toComment(CommentEntity entity) {
        Comment dto = new Comment();
        dto.setAuthor(entity.getAuthor().getId());
        if (entity.getAuthor().getImage() != null) {
            dto.setAuthorImage("/photo/image/" + entity.getAuthor().getImage().getId());
        }
        dto.setAuthorFirstName(entity.getAuthor().getFirstName());
        dto.setPk(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setText(entity.getText());
        return dto;
    }
}