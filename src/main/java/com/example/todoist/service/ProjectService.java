package com.example.todoist.service;

import com.example.todoist.model.Project;
import com.example.todoist.responseBean.ProjectResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectResponse> getAllProjects();

    ProjectResponse saveProject(Project project);

    ProjectResponse findProjectById(Integer id);

    void deleteProject(Integer id);

    Project getOneProjectById(Integer id);
}