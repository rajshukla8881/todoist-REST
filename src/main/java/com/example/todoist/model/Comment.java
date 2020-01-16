package com.example.todoist.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    Integer taskId;

    Integer projectId;

    String posted;

    @Column(columnDefinition = "TEXT")
    String content;

    @OneToMany
    List<Attachment> attachmentList;


}