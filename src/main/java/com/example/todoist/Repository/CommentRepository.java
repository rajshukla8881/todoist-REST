package com.example.todoist.Repository;

import com.example.todoist.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findByProjectId(Integer id);
    Comment findByTaskId(Integer id);

    @Override
    Optional<Comment> findById(Integer integer);

    @Override
    void deleteById(Integer integer);
}
