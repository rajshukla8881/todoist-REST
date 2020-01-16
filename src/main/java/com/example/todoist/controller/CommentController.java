package com.example.todoist.controller;

import com.example.todoist.model.Attachment;
import com.example.todoist.model.Comment;
import com.example.todoist.service.CommentService;
import com.example.todoist.requestBean.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/v1")
public class CommentController {

    @Autowired
    CommentService commentService;


    @GetMapping("/comments/{task_id}")
    public ResponseEntity getAllCommentByTaskId(@PathVariable("task_id") Integer taskId)
    {
        return new ResponseEntity(commentService.getAllCommentByTaskId(taskId), HttpStatus.OK);
    }

    @GetMapping("/comments/{project_id}")
    public ResponseEntity getAllCommentByProjectId(@PathVariable("project_id") Integer projectId)
    {
        return new ResponseEntity(commentService.getAllCommentByProjectId(projectId), HttpStatus.OK);
    }

    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity createComment(@RequestBody CommentRequest commentRequest)
    {
        Comment comment=new Comment();
        comment.setContent(commentRequest.getContent());
        if(commentRequest.getProjectId()!=null)
        {
            comment.setProjectId(commentRequest.getProjectId());
        }
        else
        {
            comment.setTaskId(comment.getTaskId());
        }

        if(commentRequest.getAttachment()!=null)
        {
            Attachment attachment=(Attachment)commentRequest.getAttachment();
            // set Attachment Details Here

        }

        commentService.saveComment(comment);
        return new ResponseEntity(comment,HttpStatus.OK);

    }

    @GetMapping("/comments/{id}")
    public ResponseEntity getCommentById(@PathVariable("id")Integer id)
    {
        return new ResponseEntity(commentService.getCommentById(id),HttpStatus.OK);
    }

    @PostMapping("/comments/{id}")
    public ResponseEntity updateCommentById(@PathVariable("id")Integer id,@RequestBody CommentRequest commentRequest)
    {
        Optional<Comment> commentOptional=commentService.getCommentById(id);
        if(commentOptional.isPresent())
        {
            Comment comment=commentOptional.get();
            comment.setContent(commentRequest.getContent());
            commentService.saveComment(comment);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity deleteCommentById(@PathVariable("id")Integer id)
    {
        commentService.deleteCommentById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
