package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    AdRepository adRepository;
    CommentRepository commentRepository;
    UserRepository userRepository;

    public CommentServiceImpl(AdRepository adRepository,
                              CommentRepository commentRepository,
                              UserRepository userRepository) {
        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Comments getComments(int id) {
        log.info("Run method getComments in service");
        List<Comment> comments = commentRepository.findAllByAdId(id).stream()
                .map(CommentMapper::toComment).collect(Collectors.toList());
        return new Comments(comments.size(), comments);
    }

    @Override
    public Comment addComment(int id, CreateOrUpdateComment comment, Authentication authentication) {
        log.info("Run method addComment in service");
        CommentEntity entity = new CommentEntity();
        entity.setText(comment.getText());
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setAuthor(userRepository.getByUsername(authentication.getName()));
        entity.setAd(adRepository.getAdById(id));
        return CommentMapper.toComment(commentRepository.save(entity));
    }

    @Override
    public void deleteComment(int commentId) {
        log.info("Run method deleteComment in service");
        CommentEntity comment = commentRepository.getCommentById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    public Comment updateComment(int commentId, CreateOrUpdateComment updateComment) {
        log.info("Run method updateComment in service");
        CommentEntity entity = commentRepository.getCommentById(commentId);
        entity.setText(updateComment.getText());
        return CommentMapper.toComment(commentRepository.save(entity));
    }

    public boolean isAuthorComment(String username, int commentId) {
        log.info("Run method isAuthorAd");
        CommentEntity comment = commentRepository.getCommentById(commentId);
        return comment.getAuthor().getUsername().equals(username);
    }
}