package com.example.todoist.serviceImpl;

import com.example.todoist.model.Due;
import com.example.todoist.model.Label;
import com.example.todoist.model.Task;
import com.example.todoist.repository.DueDAO;
import com.example.todoist.repository.TaskDAO;
import com.example.todoist.requestBean.DueRequest;
import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Autowired
    DueDAO dueDAO;

    @Override
    public List<TaskRequest> getActiveTasks() {
        log.info("request hit");
        List<TaskRequest> activeTaskRequestList = new ArrayList<>();
        List<Task> taskList = taskDAO.findAll();
        for (Task task : taskList) {
            if (task.getCompleted() == false) {
                TaskRequest taskResponse = TaskRequest.builder()
                        .id(task.getId())
                        .projectId(task.getProjectId())
                        .sectionId(task.getSectionId())
                        .content(task.getContent())
                        .commentCount(task.getCommentCount())
                        .order(task.getOrders())
                        .priority(task.getPriority())
                        .url(task.getUrl())
                        .build();
                activeTaskRequestList.add(taskResponse);
            }
        }
        return activeTaskRequestList;
    }


    @Override
    public TaskRequest createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setProjectId(taskRequest.getProjectId());
        task.setSectionId(taskRequest.getSectionId());
        task.setContent(taskRequest.getContent());
        task.setCompleted(taskRequest.getCompleted());
        task.setParent(taskRequest.getParent());
        task.setOrders(taskRequest.getOrder());
        task.setIndent(taskRequest.getIndent());
        task.setPriority(taskRequest.getPriority());
        task.setUrl(taskRequest.getUrl());
        task.setCommentCount(taskRequest.getCommentCount());
        Due due = null;
        if (dueDAO.existsByString(taskRequest.getDue().getString())) {
            due = dueDAO.findDueByString(taskRequest.getDue().getString());
        } else {
            due = new Due();
            due.setString(taskRequest.getDue().getString());
            due.setDate(taskRequest.getDue().getDate());
            due.setDateTime(taskRequest.getDue().getDateTime());
            due.setTimezone(taskRequest.getDue().getTimezone());
        }
        task.setDue(due);
        List<Label> labelIds = new ArrayList<>();
        for (Integer id : taskRequest.getLabelIds()) {
            Label label = new Label();
            label.setId(id);
            labelIds.add(label);
        }
        task.setLabelIds(labelIds);

        DueRequest dueRequest = new DueRequest();
        dueRequest.setDate(task.getDue().getDate());
        dueRequest.setDateTime(task.getDue().getDateTime());
        dueRequest.setString(task.getDue().getString());
        dueRequest.setTimezone(task.getDue().getTimezone());

        TaskRequest taskResponse = TaskRequest.builder()
                .commentCount(task.getCommentCount())
                .completed(task.getCompleted())
                .content(task.getContent())
                .due(dueRequest)
                .id(task.getId())
                .order(task.getOrders())
                .priority(task.getPriority())
                .projectId(task.getProjectId())
                .sectionId(task.getSectionId())
                .url(task.getUrl())
                .build();
        return taskResponse;
    }


    @Override
    public TaskRequest getActiveTask(int id) {
        Task task = taskDAO.getOne(id);
        DueRequest dueRequest = new DueRequest();
        dueRequest.setDate(task.getDue().getDate());
        dueRequest.setDateTime(task.getDue().getDateTime());
        dueRequest.setString(task.getDue().getString());
        dueRequest.setTimezone(task.getDue().getTimezone());
        TaskRequest taskResponse = TaskRequest.builder()
                .commentCount(task.getCommentCount())
                .completed(task.getCompleted())
                .content(task.getContent())
                .due(dueRequest)
                .id(task.getId())
                .order(task.getOrders())
                .priority(task.getPriority())
                .projectId(task.getProjectId())
                .sectionId(task.getSectionId())
                .url(task.getUrl())
                .build();
        return taskResponse;
    }


    @Override
    public void updateTask(int id, TaskRequest taskRequest) {
        Task task = taskDAO.getOne(id);
        task.setProjectId(taskRequest.getProjectId());
        task.setSectionId(taskRequest.getSectionId());
        task.setContent(taskRequest.getContent());
        task.setCompleted(taskRequest.getCompleted());
        task.setParent(taskRequest.getParent());
        task.setOrders(taskRequest.getOrder());
        task.setIndent(taskRequest.getIndent());
        task.setPriority(taskRequest.getPriority());
        task.setUrl(taskRequest.getUrl());
        task.setCommentCount(taskRequest.getCommentCount());
        Due due = null;
        if (dueDAO.existsByString(taskRequest.getDue().getString())) {
            due = dueDAO.findDueByString(taskRequest.getDue().getString());
        } else {
            due = new Due();
            due.setString(taskRequest.getDue().getString());
            due.setDate(taskRequest.getDue().getDate());
            due.setDateTime(taskRequest.getDue().getDateTime());
            due.setTimezone(taskRequest.getDue().getTimezone());
        }
        task.setDue(due);
        List<Label> labelIds = new ArrayList<>();
        for (Integer labelId : taskRequest.getLabelIds()) {
            Label label = new Label();
            label.setId(labelId);
            labelIds.add(label);
        }
        task.setLabelIds(labelIds);
    }


    @Override
    public void closeTask(int id) {
        Task task = taskDAO.getOne(id);
        task.setCompleted(true);
    }


    @Override
    public void reopenTask(int id) {
        Task task = taskDAO.getOne(id);
        task.setCompleted(false);
    }


    @Override
    public void deleteTask(int id) {
        taskDAO.deleteById(id);
    }
}