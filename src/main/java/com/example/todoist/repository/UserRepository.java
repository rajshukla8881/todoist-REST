package com.example.todoist.repository;

import com.example.todoist.model.TodoistUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TodoistUsers,Integer> {


    TodoistUsers findByUserName(String username);

}
