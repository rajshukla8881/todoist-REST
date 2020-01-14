package com.example.todoist.Controllers;

import com.example.todoist.Models.Project;
import com.example.todoist.Services.ProjectService;
import com.example.todoist.requestBean.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v1")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    private ResponseEntity getAllProjects()
    {
        List<Project> projectList=projectService.getAllProjects();
        return ResponseEntity.ok(projectList);

    }


    @PostMapping("/projects")
    @ResponseBody
    private ResponseEntity<?> addProject(@RequestBody ProjectRequest projectRequest)
    {
        Project project=new Project(projectRequest.getName());
        projectService.saveProject(project);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/projects/{id}")
    private ResponseEntity getProject(@PathVariable("id")Integer id)
    {
        Optional<Project> projectOptional=projectService.findProjectById(id);
        return ResponseEntity.ok(projectOptional.get());
    }

    @PostMapping("/projects/{id}")
    private ResponseEntity updateProject(@PathVariable("id")Integer id,@RequestBody ProjectRequest projectRequest)
    {
        Optional<Project> projectOptional=projectService.findProjectById(id);
        Project project=projectOptional.get();
        project.setName(projectRequest.getName());
        projectService.saveProject(project);
        return new ResponseEntity(HttpStatus.OK);

    }


    @DeleteMapping("projects/{id}")
    private void deleteProject(@PathVariable("id")Integer id)
    {
        projectService.deleteProject(id);
    }






}
