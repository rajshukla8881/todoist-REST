package com.example.todoist.repository;

import com.example.todoist.model.TodoistUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TodoistUser,Integer> {

    TodoistUser findByEmail(String email);
}
