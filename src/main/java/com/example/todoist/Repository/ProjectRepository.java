package com.example.todoist.Repository;

import com.example.todoist.Models.Project;
import com.example.todoist.Models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProjectRepository extends JpaRepository<Project,Integer> {

    @Override
    List<Project> findAll();

    @Override
    Optional<Project> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
