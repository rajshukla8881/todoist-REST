package com.example.todoist.requestBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    private int id;
    private String name;
    private int parent;
    private int orders;
    private int comment_count;
}