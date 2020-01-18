package com.example.todoist.serviceImplementer;

import com.example.todoist.model.*;
import com.example.todoist.repository.*;
import com.example.todoist.requestBean.DueRequest;
import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.responseBean.ActiveTaskResponse;
import com.example.todoist.responseBean.CreateTaskResponse;
import com.example.todoist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    DueDAO dueDAO;

    @Autowired
    LabelDAO labelDAO;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Override
    public List<ActiveTaskResponse> getActiveTasks() {
        List<ActiveTaskResponse> activeTaskRequestList = new ArrayList<>();
        List<Task> taskList = taskDAO.findAll();
        for (Task task : taskList) {
            if (task.getCompleted() == false) {
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
        }
        return activeTaskRequestList;
    }


    @Override
    public CreateTaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task();

        if (taskRequest.getContent() == null || taskRequest.getContent().trim().length() == 0) {
            return null;
        }
        task.setContent(taskRequest.getContent().trim());



        //Set project Id
        if (projectRepository.existsById(taskRequest.getProjectId()))
            task.setProjectId(taskRequest.getProjectId());
        else
            task.setProjectId(0);

        //Adding Task To Particular Project - MAP Open
        Project project=projectRepository.getOne(task.getProjectId());
        project.getTaskList().add(task);
        projectRepository.save(project);
        //Map Close



        //Set Section Id
        if (sectionRepository.existsById(taskRequest.getSectionId()))
            task.setSectionId(taskRequest.getSectionId());
        else
            task.setSectionId(0);

        //Adding Task To Particular Section - MAP Open
        Section section =sectionRepository.getOne(task.getSectionId());
        section.getTaskList().add(task);
        sectionRepository.save(section);
        //Map Close

        task.setCompleted(taskRequest.getCompleted());
        task.setParent(taskRequest.getParent());
        task.setOrders(taskRequest.getOrder());
        task.setIndent(taskRequest.getIndent());
        task.setPriority(taskRequest.getPriority());
        task.setUrl(taskRequest.getUrl());
        task.setCommentCount(taskRequest.getCommentCount());
        Due due = null;
        if (!(taskRequest.getDue() == null) && dueDAO.existsByString(taskRequest.getDue().getString())) {
            due = dueDAO.findDueByString(taskRequest.getDue().getString());
        } else if (!(taskRequest.getDue() == null)) {
            due = new Due();
            due.setString(taskRequest.getDue().getString());
            due.setDate(taskRequest.getDue().getDate());
            due.setDateTime(taskRequest.getDue().getDateTime());
            due.setTimezone(taskRequest.getDue().getTimezone());
            dueDAO.save(due);
        }
        task.setDue(due);
        List<Label> labelList = new ArrayList<>();
        if (!(taskRequest.getLabelIds() == null)) {
            for (Integer id : taskRequest.getLabelIds()) {
                Label label = new Label();
                if (!labelDAO.existsById(id)) {
                    label.setId(id);
                    labelDAO.save(label);
                } else {
                    label = labelDAO.getOne(id);
                }
                labelList.add(label);
            }
        }
        task.setLabelList(labelList);
        Task saveTask = taskDAO.save(task);

        DueRequest dueRequest = new DueRequest();
        if (!(saveTask.getDue() == null)) {
            dueRequest.setDate(saveTask.getDue().getDate());
            dueRequest.setDateTime(saveTask.getDue().getDateTime());
            dueRequest.setString(saveTask.getDue().getString());
            dueRequest.setTimezone(saveTask.getDue().getTimezone());
        }
        CreateTaskResponse createTaskResponse = CreateTaskResponse.builder()
                .comment_count(saveTask.getCommentCount())
                .completed(saveTask.getCompleted())
                .content(saveTask.getContent())
                .due(dueRequest)
                .id(saveTask.getId())
                .order(saveTask.getOrders())
                .priority(saveTask.getPriority())
                .project_id(saveTask.getProjectId())
                .section_id(saveTask.getSectionId())
                .url(saveTask.getUrl())
                .build();
        return createTaskResponse;
    }


    @Override
    public CreateTaskResponse getActiveTask(int id) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (optionalTask.isPresent()) {
            Task task = taskDAO.getOne(id);
            DueRequest dueRequest = new DueRequest();
            if (!(task.getDue() == null)) {
                dueRequest.setDate(task.getDue().getDate());
                dueRequest.setDateTime(task.getDue().getDateTime());
                dueRequest.setString(task.getDue().getString());
                dueRequest.setTimezone(task.getDue().getTimezone());
            }
            CreateTaskResponse createTaskResponse = CreateTaskResponse.builder()
                    .comment_count(task.getCommentCount())
                    .completed(task.getCompleted())
                    .content(task.getContent())
                    .due(dueRequest)
                    .id(task.getId())
                    .order(task.getOrders())
                    .priority(task.getPriority())
                    .project_id(task.getProjectId())
                    .section_id(task.getSectionId())
                    .url(task.getUrl())
                    .build();
            return createTaskResponse;
        } else
            return null;
    }


    @Override
    public int updateTask(int id, TaskRequest taskRequest) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (optionalTask.isPresent()) {
            Task task = taskDAO.getOne(id);
            if (taskRequest.getContent() == null || taskRequest.getContent().trim().length() == 0) {
                return -1;
            }
            task.setContent(taskRequest.getContent().trim());

            //Set project Id
            if (projectRepository.existsById(taskRequest.getProjectId()))
                task.setProjectId(taskRequest.getProjectId());
            else
                task.setProjectId(0);

            //Set Section Id
            if (sectionRepository.existsById(taskRequest.getSectionId()))
                task.setSectionId(taskRequest.getSectionId());
            else
                task.setSectionId(0);

            task.setCompleted(taskRequest.getCompleted());
            task.setParent(taskRequest.getParent());
            task.setOrders(taskRequest.getOrder());
            task.setIndent(taskRequest.getIndent());
            task.setPriority(taskRequest.getPriority());
            task.setUrl(taskRequest.getUrl());
            task.setCommentCount(taskRequest.getCommentCount());
            Due due = null;
            if (!(taskRequest.getDue() == null) && dueDAO.existsByString(taskRequest.getDue().getString())) {
                due = dueDAO.findDueByString(taskRequest.getDue().getString());
            } else if (!(taskRequest.getDue() == null)) {
                due = new Due();
                due.setString(taskRequest.getDue().getString());
                due.setDate(taskRequest.getDue().getDate());
                due.setDateTime(taskRequest.getDue().getDateTime());
                due.setTimezone(taskRequest.getDue().getTimezone());
                dueDAO.save(due);
            }
            task.setDue(due);
            List<Label> labelList = new ArrayList<>();
            if (!(taskRequest.getLabelIds() == null)) {
                for (Integer ids : taskRequest.getLabelIds()) {
                    Label label = new Label();
                    if (!labelDAO.existsById(ids)) {
                        label.setId(ids);
                        labelDAO.save(label);
                    } else {
                        label = labelDAO.getOne(ids);
                    }
                    labelList.add(label);
                }
            }
            task.setLabelList(labelList);
            Task saveTask = taskDAO.save(task);
            return saveTask.getId();
        } else
            return 0;
    }


    @Override
    public int closeTask(int id) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (optionalTask.isPresent()) {
            Task task = taskDAO.getOne(id);
            task.setCompleted(true);
            Task saveTask = taskDAO.save(task);
            return saveTask.getId();
        } else
            return 0;
    }


    @Override
    public int reopenTask(int id) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (optionalTask.isPresent()) {
            Task task = taskDAO.getOne(id);
            task.setCompleted(false);
            Task saveTask = taskDAO.save(task);
            return saveTask.getId();
        } else
            return 0;
    }


    @Override
    public int deleteTask(int id) {
        Optional<Task> optionalTask = taskDAO.findById(id);
        if (optionalTask.isPresent()) {
            taskDAO.deleteById(id);
            return 1;
        } else
            return 0;
    }
}