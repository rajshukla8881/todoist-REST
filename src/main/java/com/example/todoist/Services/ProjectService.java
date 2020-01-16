package com.example.todoist.Services;

import com.example.todoist.Models.Project;
import com.example.todoist.responseBean.ProjectResponse;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectResponse> getAllProjects();
    void saveProject(Project project);
    ProjectResponse findProjectById(Integer id);
    void deleteProject(Integer id);
    Project getOneProjectById(Integer id);
}
