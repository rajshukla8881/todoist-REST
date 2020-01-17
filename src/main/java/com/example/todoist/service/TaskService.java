package com.example.todoist.service;

import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.responseBean.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getActiveTasks();

    TaskRequest createTask(TaskRequest taskRequest);

    TaskRequest getActiveTask(int id);

    void updateTask(int id, TaskRequest taskRequest);

    void closeTask(int id);

    void reopenTask(int id);

    void deleteTask(int id);
}