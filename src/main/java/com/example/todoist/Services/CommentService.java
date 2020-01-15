package com.example.todoist.Services;

import com.example.todoist.Models.Comment;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface CommentService {
    Comment getAllCommentByProjectId(Integer id);
    Comment getAllCommentByTaskId(Integer id);
    void saveComment(Comment comment);
    Optional<Comment> getCommentById(Integer id);
    void deleteCommentById(Integer id);
}
