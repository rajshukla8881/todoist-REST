package com.example.todoist.repository;

import com.example.todoist.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section,Integer> {
    @Override
    List<Section> findAll();

    @Override
    Optional<Section> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Section getOne(Integer integer);
}
