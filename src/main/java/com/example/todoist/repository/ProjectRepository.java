package com.example.todoist.repository;

import com.example.todoist.model.Due;
import com.example.todoist.model.Project;
import com.example.todoist.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Project c WHERE c.id = :id")
    boolean existsById(@Param("id") int id);

    @Override
    List<Project> findAll();

    @Override
    Optional<Project> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Project getOne(Integer integer);


}
