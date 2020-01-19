package com.example.todoist.responseBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private int id;
    private String name;
    private int parent;
    private int orders;
    private int comment_count;
}