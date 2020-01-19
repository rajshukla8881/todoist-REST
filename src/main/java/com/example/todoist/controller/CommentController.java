//package com.example.todoist.controller;
//
//import com.example.todoist.model.Attachment;
//import com.example.todoist.model.Comment;
//import com.example.todoist.requestBean.AttachmentRequest;
//import com.example.todoist.responseBean.CommentResponse;
//import com.example.todoist.service.AttachmentService;
//import com.example.todoist.service.CommentService;
//import com.example.todoist.requestBean.CommentRequest;
//import com.example.todoist.service.ProjectService;
//import com.example.todoist.service.SectionService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Optional;
//
//@CrossOrigin
//@Slf4j
//@RestController
//@RequestMapping("/rest/v1")
//public class CommentController {
//
//    @Autowired
//    CommentService commentService;
//
//    @Autowired
//    AttachmentService attachmentService;
//
//    @Autowired
//    ProjectService projectService;
//
//    @Autowired
//    SectionService sectionService;
//
//    boolean checkValidCommentInput(String commentName)
//    {
//        if(commentName==null || commentName.trim().length()==0)
//        {
//            return false;
//        }
//        return true;
//    }
//
//
//
//    @GetMapping("/comments")
//    public ResponseEntity getAllCommentByTaskId(@RequestParam(name = "task_id",defaultValue = "-1") Integer taskId,
//                                                @RequestParam(name = "project_id",defaultValue = "-1") Integer projectId)
//    {
//        if(taskId!=Integer.parseInt("-1"))
//            return new ResponseEntity(commentService.getAllCommentByTaskId(taskId), HttpStatus.OK);
//        else
//            return new ResponseEntity(commentService.getAllCommentByProjectId(projectId), HttpStatus.OK);
//    }
//
////    @GetMapping("/comments")
////    public ResponseEntity getAllCommentByProjectId(@RequestParam("project_id") Integer projectId)
////    {
////        return new ResponseEntity(commentService.getAllCommentByProjectId(projectId), HttpStatus.OK);
////    }
//
//    @PostMapping("/comments")
//    @ResponseBody
//    public ResponseEntity createComment(@RequestBody CommentRequest commentRequest)
//    {
//        if(!checkValidCommentInput(commentRequest.getContent()))
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        log.info("Comment Content By commentRequest is "+commentRequest.getContent());
//        log.info("Comment TaskId By commentRequest is "+commentRequest.getTask_id());
//        log.info("Comment ProjectId By commentRequest is "+commentRequest.getProject_id());
//        Comment comment=new Comment();
//        comment.setContent(commentRequest.getContent().trim());
//        if(commentRequest.getProject_id()!=null)
//        {
//            Integer id=commentRequest.getProject_id();
//            if(projectService.findProjectById(id).getId()!=null)
//                comment.setProjectId(commentRequest.getProject_id());
//            else
//                comment.setProjectId(0);
//        }
//        else
//        {
//            log.info("Setting Task Id for Comment");
//            Integer id=commentRequest.getTask_id();
//            if(sectionService.getSectionById(id).getId()!=null)
//                comment.setProjectId(commentRequest.getProject_id());
//            else
//                comment.setProjectId(0);
//        }
//
//
//
//        if(commentRequest.getAttachment()!=null)
//        {
//            log.info("Attachment present in JSON");
//            log.info("Attachment Value Provided in JSON is "+commentRequest.getAttachment().toString());
//
//            Attachment attachment=new Attachment();
//            AttachmentRequest attachmentRequest=commentRequest.getAttachment();
//            attachment.setResourceType(attachmentRequest.getResource_type());
//            attachment.setFileUrl(attachmentRequest.getFile_url());
//            attachment.setFileType(attachmentRequest.getFile_type());
//            attachment.setFileName(attachmentRequest.getFile_name());
//            //Adding Attachment in Comment
//            comment.setAttachment(attachment);
//            //Saving Attachment in Database
//            attachmentService.saveAttachment(attachment);
//
//            // set Attachment Details Here
//
//        }
//
//
//        //Adding Current Time To Comment
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        String datePosted=simpleDateFormat.format(new Date());
//        comment.setPosted(datePosted);
//
//        log.info("Comment Content By comment Object is"+comment.getContent());
//        log.info("Comment TaskId By comment Object is "+comment.getTaskId());
//        log.info("Comment ProjectId By comment Object is "+comment.getProjectId());
//        commentService.saveComment(comment);
//
//        CommentResponse commentResponse=new CommentResponse();
//        commentResponse.setId(comment.getId());
//        commentResponse.setContent(comment.getContent());
//        if(comment.getProjectId()!=null)
//            commentResponse.setProject_id(comment.getProjectId());
//        else
//            commentResponse.setTask_id(comment.getTaskId());
//
//        commentResponse.setPosted(comment.getPosted());
//        log.info("Comment Attachment By comment Object is "+comment.getAttachment());
//        commentResponse.setAttachment(comment.getAttachment());
//
//        return new ResponseEntity(commentResponse,HttpStatus.OK);
//
//    }
//
//    @GetMapping("/comments/{id}")
//    public ResponseEntity getCommentById(@PathVariable("id")Integer id)
//    {
//        if(commentService.getCommentById(id).getId()!=null)
//            return new ResponseEntity(commentService.getCommentById(id),HttpStatus.OK);
//        else
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//
//    }
//
//    @PostMapping("/comments/{id}")
//    public ResponseEntity updateCommentById(@PathVariable("id")Integer id,@RequestBody CommentRequest commentRequest)
//    {
//        if(!checkValidCommentInput(commentRequest.getContent()))
//        {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        if(commentService.getCommentById(id).getId()!=null) {
//            Comment comment = commentService.getOneCommentById(id);
//            comment.setContent(commentRequest.getContent().trim());
//            commentService.saveComment(comment);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//    @DeleteMapping("/comments/{id}")
//    public ResponseEntity deleteCommentById(@PathVariable("id")Integer id)
//    {
//        if(commentService.getCommentById(id).getId()!=null)
//        {
//            commentService.deleteCommentById(id);
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//        else
//        {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//
//
//}
