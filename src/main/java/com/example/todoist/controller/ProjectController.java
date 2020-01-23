package com.example.todoist.controller;

import com.example.todoist.model.Project;
import com.example.todoist.requestBean.ProjectRequest;
import com.example.todoist.responseBean.ProjectResponse;
import com.example.todoist.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    boolean checkInValidProjectName(String projectName) {
        return projectName == null || projectName.trim().length() == 0;
    }

    @GetMapping("/projects")
    @ResponseBody
    private ResponseEntity getAllProjects() {
        return new ResponseEntity(projectService.getAllProjects(), HttpStatus.OK);
    }

    @PostMapping("/projects")
    @ResponseBody
    private ResponseEntity<?> addProject(@RequestBody ProjectRequest projectRequest) {

        //Check if the Project Name is Valid or Not
        if (checkInValidProjectName(projectRequest.getName())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Project project;

        if (projectRequest.getParent() == null)
            project = new Project(projectRequest.getName().trim());
        else
            project = new Project(projectRequest.getName().trim(), projectRequest.getParent());

        projectService.saveProject(project);
        ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.setId(project.getId());
        projectResponse.setName(project.getName());

        projectResponse.setComment_count(project.getCommentCount());
        projectResponse.setOrder(project.getProjectOrder());
        return ResponseEntity.ok(projectResponse);
    }

    @GetMapping("/projects/{id}")
    private ResponseEntity getProject(@PathVariable("id") Integer id) {
        if (projectService.findProjectById(id).getId() != null)
            return ResponseEntity.ok(projectService.findProjectById(id));
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/projects/{id}")
    private ResponseEntity updateProject(@PathVariable("id") Integer id, @RequestBody ProjectRequest projectRequest) {

        //Check if the Project Name is Valid or Not
        if (checkInValidProjectName(projectRequest.getName())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (projectService.findProjectById(id).getId() != null) {
            Project project = projectService.getOneProjectById(id);
            project.setName(projectRequest.getName().trim());
            projectService.saveProject(project);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("projects/{id}")
    private ResponseEntity deleteProject(@PathVariable("id") Integer id) {
        if (projectService.findProjectById(id).getId() != null) {
            projectService.deleteProject(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}