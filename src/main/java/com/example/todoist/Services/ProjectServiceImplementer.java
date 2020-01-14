package com.example.todoist.Services;

import com.example.todoist.Models.Project;
import com.example.todoist.Repository.ProjectRepository;
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
