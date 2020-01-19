package com.example.todoist.controller;

import com.example.todoist.requestBean.ProjectRequest;
import com.example.todoist.responseBean.ProjectResponse;
import com.example.todoist.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    @ResponseBody
    public ResponseEntity<List<ProjectResponse>> getAllProjectsList() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @PostMapping("/projects")
    @ResponseBody
    public ResponseEntity<ProjectResponse> projectCreation(@RequestBody ProjectRequest projectRequest) {
        ProjectResponse projectResponse = projectService.createNewProject(projectRequest);
        if (projectResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    @ResponseBody
    public ResponseEntity<ProjectResponse> getAProject(@PathVariable("id") int id) {
        ProjectResponse projectResponse = projectService.getProject(id);
        if (projectResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }

    @PostMapping("/projects/{id}")
    @ResponseBody
    public ResponseEntity<String> projectUpdation(@PathVariable("id") int id, @RequestBody ProjectRequest projectRequest) {
        int obtainedId = projectService.updateProject(id, projectRequest);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (obtainedId == -1)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("projects/{id}")
    @ResponseBody
    public ResponseEntity<String> projectDeletion(@PathVariable("id") int id) {
        int obtainedId = projectService.deleteProject(id);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}