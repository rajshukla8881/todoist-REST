package com.example.todoist.responseBean;

import com.example.todoist.requestBean.DueRequest;
import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskResponse {
    private int comment_count;
    private boolean completed;
    private String content;
    private DueRequest due;
    private int id;
    private int order;
    private int priority;
    private int project_id;
    private int section_id;
    private String url;
}