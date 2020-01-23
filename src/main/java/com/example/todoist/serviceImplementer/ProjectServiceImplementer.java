package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Project;
import com.example.todoist.model.Section;
import com.example.todoist.model.Task;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.repository.SectionRepository;

import com.example.todoist.repository.TaskRepository;
import com.example.todoist.responseBean.ActiveTaskResponse;
import com.example.todoist.responseBean.ProjectResponse;
import com.example.todoist.responseBean.ProjectSectionTaskResponse;
import com.example.todoist.responseBean.SectionTaskResponse;
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
    TaskRepository taskDAO;

    @Autowired
    SectionRepository sectionRepository;

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
    public ProjectResponse saveProject(Project project) {
        ProjectResponse projectResponse=new ProjectResponse();
        projectResponse.setId(project.getId());
        projectResponse.setName(project.getName());

        projectResponse.setComment_count(project.getCommentCount());
        projectResponse.setOrder(project.getProjectOrder());
        projectRepository.save(project);
        return projectResponse;
    }

    @Override
    public ProjectSectionTaskResponse findProjectById(Integer id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectSectionTaskResponse projectSectionTaskResponse = new ProjectSectionTaskResponse();
            projectSectionTaskResponse.setId(project.getId());
            projectSectionTaskResponse.setName(project.getName());
            projectSectionTaskResponse.setComment_count(project.getCommentCount());
            projectSectionTaskResponse.setOrder(project.getProjectOrder());
            List<Section> sectionList = sectionRepository.getSectionByProjectId(id);
            List<SectionTaskResponse> sectionTaskResponseList = new ArrayList<>();
            for (Section section : sectionList) {
                List<Task> taskList = taskDAO.getTaskBySectionIdAndProjectId(section.getId(), id);
                List<ActiveTaskResponse> activeTaskResponseList = new ArrayList<>();
                for (Task task : taskList) {
                    ActiveTaskResponse activeTaskResponse = ActiveTaskResponse.builder()
                            .id(task.getId())
                            .project_id(task.getProjectId())
                            .section_id(task.getSectionId())
                            .content(task.getContent())
                            .comment_count(task.getComment_count())
                            .order(task.getOrders())
                            .priority(task.getPriority())
                            .url(task.getUrl())
                            .build();

                    activeTaskResponseList.add(activeTaskResponse);
                }

                SectionTaskResponse sectionTaskResponse = SectionTaskResponse.builder()
                        .id(section.getId())
                        .project_id(section.getProjectId())
                        .order(section.getSectionOrder())
                        .name(section.getName())
                        .task(activeTaskResponseList)
                        .build();

                sectionTaskResponseList.add(sectionTaskResponse);
            }

            List<Task> taskList = taskDAO.getTaskBySectionIdAndProjectId(0, id);
            List<ActiveTaskResponse> activeTaskRequestList = new ArrayList<>();
            for (Task task : taskList) {

                ActiveTaskResponse activeTaskResponse = ActiveTaskResponse.builder()
                        .id(task.getId())
                        .project_id(task.getProjectId())
                        .section_id(task.getSectionId())
                        .content(task.getContent())
                        .comment_count(task.getComment_count())
                        .order(task.getOrders())
                        .priority(task.getPriority())
                        .url(task.getUrl())
                        .build();
                activeTaskRequestList.add(activeTaskResponse);
            }
            projectSectionTaskResponse.setSection(sectionTaskResponseList);
            projectSectionTaskResponse.setTask(activeTaskRequestList);
            return projectSectionTaskResponse;
        } else {
            return new ProjectSectionTaskResponse();
        }
    }

    @Override
    public int updateProject(Project project) {

        return 0;
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