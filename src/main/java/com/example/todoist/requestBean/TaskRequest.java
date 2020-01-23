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
    private int project_id;
    private int section_id;
    private String content;
    private boolean completed;
    private List<Integer> label_ids;
    private int parent;
    private int order;
    private int priority;
    private DueRequest due;
    private String url;
    private int comment_count;
}
