package com.example.todoist.service;

import com.example.todoist.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> getAllProjects();

    void saveProject(Project project);

    Optional<Project> findProjectById(Integer id);

    void deleteProject(Integer id);
}