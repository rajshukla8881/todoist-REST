package com.example.todoist.controller;

import com.example.todoist.model.Attachment;
import com.example.todoist.model.Comment;
import com.example.todoist.responseBean.CommentResponse;
import com.example.todoist.service.AttachmentService;
import com.example.todoist.service.CommentService;
import com.example.todoist.requestBean.CommentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/rest/v1")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    AttachmentService attachmentService;


    @GetMapping("/comments")
    public ResponseEntity getAllCommentByTaskId(@RequestParam(name = "task_id",defaultValue = "-1") Integer taskId,
                                                @RequestParam(name = "project_id",defaultValue = "-1") Integer projectId)
    {
        if(taskId!=Integer.parseInt("-1"))
            return new ResponseEntity(commentService.getAllCommentByTaskId(taskId), HttpStatus.OK);
        else
            return new ResponseEntity(commentService.getAllCommentByProjectId(projectId), HttpStatus.OK);
    }

//    @GetMapping("/comments")
//    public ResponseEntity getAllCommentByProjectId(@RequestParam("project_id") Integer projectId)
//    {
//        return new ResponseEntity(commentService.getAllCommentByProjectId(projectId), HttpStatus.OK);
//    }

    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity createComment(@RequestBody CommentRequest commentRequest)
    {
        log.info("Comment Content By commentRequest is "+commentRequest.getContent());
        log.info("Comment TaskId By commentRequest is "+commentRequest.getTask_id());
        log.info("Comment ProjectId By commentRequest is "+commentRequest.getProject_id());
        Comment comment=new Comment();
        comment.setContent(commentRequest.getContent());
        if(commentRequest.getProject_id()!=null)
        {
            comment.setProjectId(commentRequest.getProject_id());
        }
        else
        {
            log.info("Setting Task Id for Comment");
            comment.setTaskId(commentRequest.getTask_id());
        }

        if(commentRequest.getAttachment()!=null)
        {
            Attachment attachment=new Attachment();
            Attachment commentRequestAttachment=(Attachment)commentRequest.getAttachment();
            attachment.setResourceType(commentRequestAttachment.getResourceType());
            attachment.setFileUrl(commentRequestAttachment.getFileUrl());
            attachment.setFileType(commentRequestAttachment.getFileType());
            attachment.setFileName(commentRequestAttachment.getFileName());
            attachmentService.saveAttachment(attachment);
            // set Attachment Details Here

        }


        //Adding Current Time To Comment
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String datePosted=simpleDateFormat.format(new Date());
        comment.setPosted(datePosted);

        log.info("Comment Content By comment Object is"+comment.getContent());
        log.info("Comment TaskId By comment Object is "+comment.getTaskId());
        log.info("Comment ProjectId By comment Object is "+comment.getProjectId());
        commentService.saveComment(comment);

        CommentResponse commentResponse=new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        if(comment.getProjectId()!=null)
            commentResponse.setProject_id(comment.getProjectId());
        else
            commentResponse.setTask_id(comment.getTaskId());

        commentResponse.setPosted(comment.getPosted());
        commentResponse.setAttachment(comment.getAttachment());

        return new ResponseEntity(commentResponse,HttpStatus.OK);

    }

    @GetMapping("/comments/{id}")
    public ResponseEntity getCommentById(@PathVariable("id")Integer id)
    {
        return new ResponseEntity(commentService.getCommentById(id),HttpStatus.OK);
    }

    @PostMapping("/comments/{id}")
    public ResponseEntity updateCommentById(@PathVariable("id")Integer id,@RequestBody CommentRequest commentRequest)
    {

            Comment comment=commentService.getOneCommentById(id);
            comment.setContent(commentRequest.getContent());
            commentService.saveComment(comment);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity deleteCommentById(@PathVariable("id")Integer id)
    {
        commentService.deleteCommentById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
