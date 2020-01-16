package com.example.todoist.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

//@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileId;

    private long fileSize;
    private String fileType;
    private String fileName;
    private String uploadState;
    private String resourceType;
    private String fileUrl;

/*    @ManyToOne
    private Comment comment;*/
}