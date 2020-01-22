package com.example.todoist.repository;

import com.example.todoist.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Label c WHERE c.id = :id")
    boolean existsById(@Param("id") int id);

    int findById(String string);

    @Override
    List<Label> findAll();

    @Override
    Optional<Label> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Label getOne(Integer integer);
}
