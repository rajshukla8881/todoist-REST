package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Project;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.responseBean.ProjectResponse;
import com.example.todoist.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImplementer implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<ProjectResponse> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        for (Project projectListIterator : projectList) {
            ProjectResponse projectResponse = new ProjectResponse();
            projectResponse.setId(projectListIterator.getId());
            projectResponse.setName(projectListIterator.getName());
            projectResponse.setComment_count(projectListIterator.getCommentCount());
            projectResponse.setOrder(projectListIterator.getProjectOrder());
            projectResponseList.add(projectResponse);
        }
        return projectResponseList;
    }

    @Override
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public ProjectResponse findProjectById(Integer id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectResponse projectResponse = new ProjectResponse();
            projectResponse.setId(project.getId());
            projectResponse.setName(project.getName());
            projectResponse.setComment_count(project.getCommentCount());
            projectResponse.setOrder(project.getProjectOrder());
            return projectResponse;
        } else {
            return new ProjectResponse();
        }
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getOneProjectById(Integer id) {
        return projectRepository.getOne(id);
    }
}