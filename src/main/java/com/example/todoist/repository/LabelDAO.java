package com.example.todoist.repository;

import com.example.todoist.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelDAO extends JpaRepository<Label, Integer> {
}