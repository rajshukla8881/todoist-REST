package com.example.todoist.responseBean;


import lombok.Data;

@Data
public class ProjectResponse {
    Integer id;
    String name;
    Integer comment_count;
    Integer order;
}
