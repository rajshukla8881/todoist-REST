package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Due;
import com.example.todoist.model.Label;
import com.example.todoist.model.Task;
import com.example.todoist.repository.DueDAO;
import com.example.todoist.repository.LabelDAO;
import com.example.todoist.repository.TaskDAO;
import com.example.todoist.requestBean.DueRequest;
import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.responseBean.TaskResponse;
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

    @Autowired
    LabelDAO labelDAO;

    @Override
    public List<TaskResponse> getActiveTasks() {
        List<TaskResponse> activeTaskRequestList = new ArrayList<>();
        List<Task> taskList = taskDAO.findAll();
        for (Task task : taskList) {
            if (task.getCompleted() == false) {
                TaskResponse taskResponse = TaskResponse.builder()
                        .id(task.getId())
                        .project_id(task.getProjectId())
                        .section_id(task.getSectionId())
                        .content(task.getContent())
                        .comment_count(task.getCommentCount())
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
        if (!(taskRequest.getLabelIds()==null)) {
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
        TaskRequest taskResponse = TaskRequest.builder()
                .commentCount(saveTask.getCommentCount())
                .completed(saveTask.getCompleted())
                .content(saveTask.getContent())
                .due(dueRequest)
                .id(saveTask.getId())
                .order(saveTask.getOrders())
                .priority(saveTask.getPriority())
                .projectId(saveTask.getProjectId())
                .sectionId(saveTask.getSectionId())
                .url(saveTask.getUrl())
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
            dueDAO.save(due);
        }
        task.setDue(due);
        List<Label> labelList = new ArrayList<>();
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
        task.setLabelList(labelList);
        taskDAO.save(task);
    }


    @Override
    public void closeTask(int id) {
        Task task = taskDAO.getOne(id);
        task.setCompleted(true);
        taskDAO.save(task);
    }


    @Override
    public void reopenTask(int id) {
        Task task = taskDAO.getOne(id);
        task.setCompleted(false);
        taskDAO.save(task);
    }


    @Override
    public void deleteTask(int id) {
        taskDAO.deleteById(id);
    }
}