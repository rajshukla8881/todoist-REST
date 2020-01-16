package com.example.todoist.repository;

import com.example.todoist.model.Due;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DueDAO extends JpaRepository<Due, String> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Due c WHERE c.string = :string")
    boolean existsByString(@Param("string") String string);

    Due findDueByString(String string);
}