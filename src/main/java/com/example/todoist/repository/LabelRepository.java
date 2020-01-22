package com.example.todoist.repository;

import com.example.todoist.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {

    @Override
    List<Label> findAll();

    @Override
    Optional<Label> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Label getOne(Integer integer);
}
