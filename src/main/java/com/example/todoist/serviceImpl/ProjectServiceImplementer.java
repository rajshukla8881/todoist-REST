package com.example.todoist.serviceImpl;

import com.example.todoist.model.Project;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImplementer implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}