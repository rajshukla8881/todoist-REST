package com.example.todoist.repository;

import com.example.todoist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDAO extends JpaRepository<Task, Integer> {
    //List<Task> getTaskByProjectId(Integer id);

    List<Task> getTaskBySectionIdAndProjectId(Integer sectionId,Integer projectId);



}