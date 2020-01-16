package com.example.todoist.controller;

import com.example.todoist.model.Project;
import com.example.todoist.service.ProjectService;
import com.example.todoist.requestBean.ProjectRequest;
import com.example.todoist.responseBean.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    @ResponseBody
    private ResponseEntity getAllProjects()
    {
        return new ResponseEntity(projectService.getAllProjects(), HttpStatus.OK);

    }


    @PostMapping("/projects")
    @ResponseBody
    private ResponseEntity<?> addProject(@RequestBody ProjectRequest projectRequest)
    {

        Project project;

       if(projectRequest.getParent()==null)
           project = new Project(projectRequest.getName());
       else
           project=new Project(projectRequest.getName(),projectRequest.getParent());


       projectService.saveProject(project);
       ProjectResponse projectResponse=new ProjectResponse();
       projectResponse.setId(project.getId());
       projectResponse.setName(project.getName());
       projectResponse.setComment_count(project.getCommentCount());
       projectResponse.setOrder(project.getProjectOrder());
       return ResponseEntity.ok(projectResponse);
    }

    @GetMapping("/projects/{id}")
    private ResponseEntity getProject(@PathVariable("id")Integer id)
    {
        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    @PostMapping("/projects/{id}")
    private ResponseEntity updateProject(@PathVariable("id")Integer id,@RequestBody ProjectRequest projectRequest)
    {

        Project project=projectService.getOneProjectById(id);
        project.setName(projectRequest.getName());
        projectService.saveProject(project);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


    @DeleteMapping("projects/{id}")
    private ResponseEntity deleteProject(@PathVariable("id")Integer id)
    {
        projectService.deleteProject(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }






}
