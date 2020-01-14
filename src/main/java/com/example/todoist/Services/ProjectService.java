package com.example.todoist.Services;

import com.example.todoist.Models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getAllProjects();
    void saveProject(Project project);
    Optional<Project> findProjectById(Integer id);
    void deleteProject(Integer id);
}
