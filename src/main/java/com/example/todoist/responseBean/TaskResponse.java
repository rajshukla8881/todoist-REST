package com.example.todoist.responseBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    private int id;
    private int project_id;
    private int section_id;
    private String content;
    private int comment_count;
    private int order;
    private int priority;
    private String url;
}