package com.example.todoist.responseBean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SectionTaskResponse {
    Integer id;
    Integer project_id;
    Integer order;
    String name;
    List<ActiveTaskResponse> task;
}