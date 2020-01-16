package com.example.todoist.service;

import com.example.todoist.requestBean.TaskRequest;

import java.util.List;

public interface TaskService {

    List<TaskRequest> getActiveTasks();

    TaskRequest createTask(TaskRequest taskRequest);

    TaskRequest getActiveTask(int id);

    void updateTask(int id, TaskRequest taskRequest);

    void closeTask(int id);

    void reopenTask(int id);

    void deleteTask(int id);
}