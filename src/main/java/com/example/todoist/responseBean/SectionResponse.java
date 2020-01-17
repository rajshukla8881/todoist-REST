package com.example.todoist.responseBean;

import lombok.Data;

@Data
public class SectionResponse {
    Integer id;
    Integer project_id;
    Integer order;
    String name;
}