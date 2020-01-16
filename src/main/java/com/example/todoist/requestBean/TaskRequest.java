package com.example.todoist.requestBean;

import lombok.*;

import java.util.List;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private int id;
    private int projectId;
    private int sectionId;
    private String content;
    private Boolean completed;
    private List<Integer> labelIds;
    private int parent;
    private int order;
    private int indent;
    private int priority;
    private DueRequest due;
    private String url;
    private int commentCount;

}