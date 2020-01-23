package com.example.todoist.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@CrossOrigin
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer taskId;

    @Column(nullable = true)
    Integer projectId;

    String posted;

    @Column(columnDefinition = "TEXT")
    String content;

    @OneToOne
    Attachment attachment;
}