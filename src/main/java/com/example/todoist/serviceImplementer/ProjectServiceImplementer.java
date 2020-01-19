package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Project;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.requestBean.ProjectRequest;
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
        for (Project project : projectList) {
            ProjectResponse projectResponse = ProjectResponse.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .orders(project.getOrders())
                    .comment_count(project.getComment_count())
                    .parent(project.getParent())
                    .build();
            projectResponseList.add(projectResponse);
        }
        return projectResponseList;
    }


    @Override
    public ProjectResponse createNewProject(ProjectRequest projectRequest) {
        Project project = new Project();
        if (projectRequest.getName() == null || projectRequest.getName().trim().length() == 0) {
            return null;
        }
        project.setName(projectRequest.getName().trim());
        project.setComment_count(projectRequest.getComment_count());
        project.setOrders(projectRequest.getOrders());
        projectRepository.save(project);
        ProjectResponse projectResponse = ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .orders(project.getOrders())
                .comment_count(project.getComment_count())
                .build();
        return projectResponse;
    }


    @Override
    public ProjectResponse getProject(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = projectRepository.getOne(id);
            ProjectResponse projectResponse = ProjectResponse.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .orders(project.getOrders())
                    .comment_count(project.getComment_count())
                    .build();
            return projectResponse;
        }
        return null;
    }


    @Override
    public int updateProject(int id, ProjectRequest projectRequest) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = projectRepository.getOne(id);
            if (projectRequest.getName() == null || projectRequest.getName().trim().length() == 0) {
                return -1;
            }
            project.setName(projectRequest.getName().trim());
            project.setComment_count(projectRequest.getComment_count());
            project.setOrders(projectRequest.getOrders());
            projectRepository.save(project);
            return projectRepository.save(project).getId();
        }
        return 0;
    }


    @Override
    public int deleteProject(int id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            projectRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}