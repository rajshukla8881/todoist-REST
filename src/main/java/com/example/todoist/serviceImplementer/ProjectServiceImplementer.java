package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Project;
import com.example.todoist.model.Section;
import com.example.todoist.model.Task;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.repository.TaskDAO;
import com.example.todoist.responseBean.ActiveTaskResponse;
import com.example.todoist.responseBean.ProjectResponse;
import com.example.todoist.responseBean.ProjectSectionTaskResponse;
import com.example.todoist.responseBean.SectionResponse;
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

    @Autowired
    TaskDAO taskDAO;

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
    public ProjectSectionTaskResponse findProjectById(Integer id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectSectionTaskResponse projectSectionTaskResponse=new ProjectSectionTaskResponse();
            projectSectionTaskResponse.setId(project.getId());
            projectSectionTaskResponse.setName(project.getName());
            projectSectionTaskResponse.setComment_count(project.getCommentCount());
            projectSectionTaskResponse.setOrder(project.getProjectOrder());
            List<Task> taskList=taskDAO.getTaskByProjectId(id);
            List<ActiveTaskResponse> activeTaskRequestList = new ArrayList<>();
            for (Task task : taskList) {

                ActiveTaskResponse activeTaskResponse = ActiveTaskResponse.builder()
                        .id(task.getId())
                        .project_id(task.getProjectId())
                        .section_id(task.getSectionId())
                        .content(task.getContent())
                        .comment_count(task.getCommentCount())
                        .order(task.getOrders())
                        .priority(task.getPriority())
                        .url(task.getUrl())
                        .build();
                activeTaskRequestList.add(activeTaskResponse);
            }
            projectSectionTaskResponse.setTask(activeTaskRequestList);
            return projectSectionTaskResponse;
        } else {
            return new ProjectSectionTaskResponse();
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