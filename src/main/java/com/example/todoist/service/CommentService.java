package com.example.todoist.service;

import com.example.todoist.model.Comment;

import java.util.Optional;

public interface CommentService {
    Comment getAllCommentByProjectId(Integer id);

    Comment getAllCommentByTaskId(Integer id);

    void saveComment(Comment comment);

    Optional<Comment> getCommentById(Integer id);

    void deleteCommentById(Integer id);
}