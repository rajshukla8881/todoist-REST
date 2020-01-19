package com.example.todoist.service;

import com.example.todoist.requestBean.ProjectRequest;
import com.example.todoist.responseBean.ProjectResponse;

import java.util.List;

public interface ProjectService {

    List<ProjectResponse> getAllProjects();

    ProjectResponse createNewProject(ProjectRequest projectRequest);

    ProjectResponse getProject(int id);

    int updateProject(int id, ProjectRequest projectRequest);

    int deleteProject(int id);
}