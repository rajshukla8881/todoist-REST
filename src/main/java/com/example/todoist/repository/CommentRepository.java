package com.example.todoist.repository;

import com.example.todoist.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByProjectId(Integer id);

    List<Comment> findAllByTaskId(Integer id);

    Comment findByProjectId(Integer id);

    Comment findByTaskId(Integer id);

    @Override
    Optional<Comment> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Comment getOne(Integer integer);
}