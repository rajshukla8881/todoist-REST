package com.example.todoist.service;

import com.example.todoist.model.Comment;
import com.example.todoist.responseBean.CommentResponse;
import com.example.todoist.responseBean.CommentResponseProject;
import com.example.todoist.responseBean.CommentResponseTask;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<CommentResponseProject> getAllCommentByProjectId(Integer id);
    List<CommentResponseTask> getAllCommentByTaskId(Integer id);
    void saveComment(Comment comment);
    CommentResponse getCommentById(Integer id);
    void deleteCommentById(Integer id);
    Comment getOneCommentById(Integer id);
}
