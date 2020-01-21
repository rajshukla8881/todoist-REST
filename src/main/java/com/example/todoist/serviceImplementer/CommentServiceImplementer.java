package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Comment;
import com.example.todoist.repository.CommentRepository;
import com.example.todoist.responseBean.CommentResponse;
import com.example.todoist.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImplementer implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<CommentResponse> getAllCommentByProjectId(Integer id) {
        List<Comment> commentList = commentRepository.findAllByProjectId(id);
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (Comment commentListIterator : commentList) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(commentListIterator.getId());
            commentResponse.setProject_id(commentListIterator.getProjectId());
            commentResponse.setContent(commentListIterator.getContent());
            commentResponse.setPosted(commentListIterator.getPosted());
            commentResponse.setAttachment(commentListIterator.getAttachment());
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }

    @Override
    public List<CommentResponse> getAllCommentByTaskId(Integer id) {
        List<Comment> commentList = commentRepository.findAllByTaskId(id);
        List<CommentResponse> commentResponseList = new ArrayList<>();
        for (Comment commentListIterator : commentList) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(commentListIterator.getId());
            commentResponse.setTask_id(commentListIterator.getTaskId());
            commentResponse.setContent(commentListIterator.getContent());
            commentResponse.setPosted(commentListIterator.getPosted());
            commentResponse.setAttachment(commentListIterator.getAttachment());
            commentResponseList.add(commentResponse);
        }
        return commentResponseList;
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public CommentResponse getCommentById(Integer id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            if (comment.getProjectId() != null)
                commentResponse.setProject_id(comment.getProjectId());
            else
                commentResponse.setTask_id(comment.getProjectId());

            commentResponse.setPosted(comment.getPosted());
            commentResponse.setAttachment(comment.getAttachment());

            return commentResponse;
        } else {
            return new CommentResponse();
        }
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getOneCommentById(Integer id) {
        return commentRepository.getOne(id);
    }
}