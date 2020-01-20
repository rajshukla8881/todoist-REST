package com.example.todoist.service;

import com.example.todoist.model.Project;
import com.example.todoist.responseBean.ProjectResponse;
import com.example.todoist.responseBean.ProjectSectionTaskResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectResponse> getAllProjects();

    void saveProject(Project project);

    ProjectSectionTaskResponse findProjectById(Integer id);

    void deleteProject(Integer id);

    Project getOneProjectById(Integer id);
}