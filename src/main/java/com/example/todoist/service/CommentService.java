package com.example.todoist.service;

import com.example.todoist.model.Comment;
import com.example.todoist.responseBean.CommentResponse;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getAllCommentByProjectId(Integer id);

    List<CommentResponse> getAllCommentByTaskId(Integer id);

    void saveComment(Comment comment);

    CommentResponse getCommentById(Integer id);

    void deleteCommentById(Integer id);

    Comment getOneCommentById(Integer id);
}