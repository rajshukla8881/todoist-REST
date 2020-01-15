package com.example.todoist.Repository;

import com.example.todoist.Models.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label,Integer> {


    @Override
    List<Label> findAll();

    @Override
    Optional<Label> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
