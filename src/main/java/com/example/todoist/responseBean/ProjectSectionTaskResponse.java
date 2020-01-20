package com.example.todoist.responseBean;

import lombok.Data;

import java.util.List;

@Data
public class ProjectSectionTaskResponse {
    Integer id;
    String name;
    Integer comment_count;
    Integer order;
   // SectionResponse section;
    List<ActiveTaskResponse> task;

}
