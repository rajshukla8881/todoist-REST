package com.example.todoist.controller;

import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/v1/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskRequest>> getActiveTaskList() {
        return new ResponseEntity<List<TaskRequest>>(taskService.getActiveTasks(), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskRequest> taskCreation(@RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<TaskRequest>(taskService.createTask(taskRequest), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskRequest> getAnActiveTask(@PathVariable("id") int id) {
        return new ResponseEntity<TaskRequest>(taskService.getActiveTask(id), HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<String> taskUpdation(@PathVariable("id") int id, TaskRequest taskRequest) {
        taskService.updateTask(id, taskRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tasks/{id}/close")
    public ResponseEntity<String> taskClosure(@PathVariable("id") int id) {
        taskService.closeTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}/reopen")
    public ResponseEntity<String> taskReopen(@PathVariable("id") int id) {
        taskService.reopenTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> taskDeletion(@PathVariable("id") int id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}