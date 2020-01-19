package com.example.todoist.repository;

import com.example.todoist.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LabelRepository extends JpaRepository<Label, Integer> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Label c WHERE c.id = :id")
    boolean existsById(@Param("id") int id);
}