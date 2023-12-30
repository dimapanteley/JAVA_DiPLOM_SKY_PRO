package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getComments(int id);

    Comment addComment(int id, CreateOrUpdateComment comment, Authentication authentication);

    void deleteComment(int commentId);

    Comment updateComment(int commentId, CreateOrUpdateComment updateComment);
}
