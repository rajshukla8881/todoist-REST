package com.example.todoist.controller;

import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.responseBean.ActiveTaskResponse;
import com.example.todoist.responseBean.CreateTaskResponse;
import com.example.todoist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class GoogleVerificationController {

    @GetMapping("/googlec4994c3d3ef026f1")
    public ResponseEntity<String> getActiveTaskList() {
        return new ResponseEntity<String>("google-site-verification: googlec4994c3d3ef026f1.html", HttpStatus.OK);
    }
    
    @GetMapping("/googlec4994c3d3ef026f1.html")
    public ResponseEntity<String> getActiveTaskListHTML() {
        return new ResponseEntity<String>("google-site-verification: googlec4994c3d3ef026f1.html", HttpStatus.OK);
    }
}
