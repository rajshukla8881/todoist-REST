package com.example.todoist;

import com.example.todoist.repository.ProjectRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

public class TodoistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoistApplication.class, args);
    }

}