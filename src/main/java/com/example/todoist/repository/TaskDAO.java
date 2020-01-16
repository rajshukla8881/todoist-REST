package com.example.todoist.repository;

import com.example.todoist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDAO extends JpaRepository<Task, Integer> {
}