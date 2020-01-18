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
@RequestMapping("/rest/v1/")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<ActiveTaskResponse>> getActiveTaskList() {
        return new ResponseEntity<List<ActiveTaskResponse>>(taskService.getActiveTasks(), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<CreateTaskResponse> taskCreation(@RequestBody TaskRequest taskRequest) {
        CreateTaskResponse createTaskResponse = taskService.createTask(taskRequest);
        if (createTaskResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<CreateTaskResponse>(createTaskResponse, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<CreateTaskResponse> getAnActiveTask(@PathVariable("id") int id) {
        CreateTaskResponse createTaskResponse = taskService.getActiveTask(id);

        if (createTaskResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<CreateTaskResponse>(createTaskResponse, HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<String> taskUpdation(@PathVariable("id") int id, @RequestBody TaskRequest taskRequest) {
        int obtainedId = taskService.updateTask(id, taskRequest);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (obtainedId == -1)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tasks/{id}/close")
    public ResponseEntity<String> taskClosure(@PathVariable("id") int id) {
        int obtainedId = taskService.closeTask(id);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}/reopen")
    public ResponseEntity<String> taskReopen(@PathVariable("id") int id) {
        int obtainedId = taskService.reopenTask(id);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> taskDeletion(@PathVariable("id") int id) {
        int obtainedId = taskService.deleteTask(id);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}