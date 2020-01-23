package com.example.todoist.service;

import com.example.todoist.requestBean.TaskRequest;
import com.example.todoist.responseBean.ActiveTaskResponse;
import com.example.todoist.responseBean.CreateTaskResponse;

import java.util.List;

public interface TaskService {

    List<ActiveTaskResponse> getActiveTasks();

    CreateTaskResponse createTask(TaskRequest taskRequest);

    CreateTaskResponse getActiveTask(int id);

    int updateTask(int id, TaskRequest taskRequest);

    int closeTask(int id);

    int reopenTask(int id);

    int deleteTask(int id);
}