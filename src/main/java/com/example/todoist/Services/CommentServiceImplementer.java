package com.example.todoist.Services;

import com.example.todoist.Models.Comment;
import com.example.todoist.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImplementer implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment getAllCommentByProjectId(Integer id) {
        return commentRepository.findByProjectId(id);
    }

    @Override
    public Comment getAllCommentByTaskId(Integer id) {
        return commentRepository.findByTaskId(id);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }
}
