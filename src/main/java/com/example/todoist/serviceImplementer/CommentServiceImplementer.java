package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Attachment;
import com.example.todoist.model.Comment;
import com.example.todoist.model.Section;
import com.example.todoist.repository.CommentRepository;
import com.example.todoist.responseBean.*;
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
    public List<CommentResponseProject> getAllCommentByProjectId(Integer id) {
        List<Comment> commentList=commentRepository.findAllByProjectId(id);
        List<CommentResponseProject> commentResponseList=new ArrayList<>();
        for(Comment commentListIterator:commentList)
        {
            CommentResponseProject commentResponseProject=new CommentResponseProject();
            commentResponseProject.setId(commentListIterator.getId());
            commentResponseProject.setProject_id(commentListIterator.getProjectId());
            commentResponseProject.setContent(commentListIterator.getContent());
            commentResponseProject.setPosted(commentListIterator.getPosted());
            if(commentListIterator.getAttachment()!=null) {
                Attachment attachment = commentListIterator.getAttachment();
                AttachmentResponse attachmentResponse = new AttachmentResponse();
                attachmentResponse.setFile_name(attachment.getFileName());
                attachmentResponse.setFile_type(attachment.getFileType());
                attachmentResponse.setFile_url(attachment.getFileUrl());
                attachmentResponse.setResource_type(attachment.getResourceType());
                commentResponseProject.setAttachment(attachmentResponse);
            }
            commentResponseList.add(commentResponseProject);

        }

        return commentResponseList;

    }

    @Override
    public List<CommentResponseTask> getAllCommentByTaskId(Integer id) {
        List<Comment> commentList=commentRepository.findAllByTaskId(id);
        List<CommentResponseTask> commentResponseList=new ArrayList<>();
        for(Comment commentListIterator:commentList)
        {
            CommentResponseTask commentResponse=new CommentResponseTask();
            commentResponse.setId(commentListIterator.getId());
            commentResponse.setTask_id(commentListIterator.getTaskId());
            commentResponse.setContent(commentListIterator.getContent());
            commentResponse.setPosted(commentListIterator.getPosted());
            if(commentListIterator.getAttachment()!=null) {
                Attachment attachment = commentListIterator.getAttachment();
                AttachmentResponse attachmentResponse = new AttachmentResponse();
                attachmentResponse.setFile_name(attachment.getFileName());
                attachmentResponse.setFile_type(attachment.getFileType());
                attachmentResponse.setFile_url(attachment.getFileUrl());
                attachmentResponse.setResource_type(attachment.getResourceType());
                commentResponse.setAttachment(attachmentResponse);
            }
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
        Optional<Comment> commentOptional=commentRepository.findById(id);
        if(commentOptional.isPresent())
        {
            Comment comment=commentOptional.get();
            CommentResponse commentResponse=new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            if(comment.getProjectId()!=null)
                commentResponse.setProject_id(comment.getProjectId());
            else
                commentResponse.setTask_id(comment.getProjectId());

            commentResponse.setPosted(comment.getPosted());
            if(comment.getAttachment()!=null) {
                Attachment attachment = comment.getAttachment();
                AttachmentResponse attachmentResponse = new AttachmentResponse();
                attachmentResponse.setFile_name(attachment.getFileName());
                attachmentResponse.setFile_type(attachment.getFileType());
                attachmentResponse.setFile_url(attachment.getFileUrl());
                attachmentResponse.setResource_type(attachment.getResourceType());
                commentResponse.setAttachment(attachmentResponse);
            }

            return commentResponse;
        }
        else
        {

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
