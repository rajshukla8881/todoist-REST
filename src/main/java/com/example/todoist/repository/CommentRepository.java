package com.example.todoist.repository;

import com.example.todoist.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findByProjectId(Integer id);
    Comment findByTaskId(Integer id);

    @Override
    Optional<Comment> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
