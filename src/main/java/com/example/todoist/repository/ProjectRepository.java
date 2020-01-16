package com.example.todoist.repository;

import com.example.todoist.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Override
    List<Project> findAll();

    @Override
    Optional<Project> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Project getOne(Integer integer);
}
