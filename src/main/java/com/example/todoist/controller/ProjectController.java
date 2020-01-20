package com.example.todoist.controller;

import com.example.todoist.model.Project;
import com.example.todoist.service.ProjectService;
import com.example.todoist.requestBean.ProjectRequest;
import com.example.todoist.responseBean.ProjectResponse;
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


    boolean checkValidProjectName(String projectName)
    {
        return projectName != null && projectName.trim().length() != 0;
    }

    @GetMapping("/projects")
    @ResponseBody
    public ResponseEntity<List<ProjectResponse>> getAllProjects()
    {
        return new ResponseEntity<List<ProjectResponse>>(projectService.getAllProjects(), HttpStatus.OK);

    }


    @PostMapping("/projects")
    @ResponseBody
    public ResponseEntity<ProjectResponse> addProject(@RequestBody ProjectRequest projectRequest)
    {

        //Check if the Project Name is Valid or Not
        if(!checkValidProjectName(projectRequest.getName()))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Project project;

        if(projectRequest.getParent()==null)
            project = new Project(projectRequest.getName());
        else
            project=new Project(projectRequest.getName().trim(),projectRequest.getParent());


        ProjectResponse projectResponse=projectService.saveProject(project);

        return new ResponseEntity<ProjectResponse>(HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    @ResponseBody
    public ResponseEntity<ProjectResponse> getProject(@PathVariable("id")Integer id)
    {
        if(projectService.findProjectById(id).getId()!=null)
        return new ResponseEntity<ProjectResponse>(projectService.findProjectById(id),HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/projects/{id}")
    @ResponseBody
    public ResponseEntity<String> updateProject(@PathVariable("id")Integer id,@RequestBody ProjectRequest projectRequest)
    {

        //Check if the Project Name is Valid or Not
        if(!checkValidProjectName(projectRequest.getName()))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if(projectService.findProjectById(id).getId()!=null)
        {
            Project project = projectService.getOneProjectById(id);
            project.setName(projectRequest.getName().trim());
            projectService.saveProject(project);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("projects/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProject(@PathVariable("id")Integer id)
    {
        if(projectService.findProjectById(id).getId()!=null)
        {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }








}
