package com.example.todoist.requestBean;

import lombok.Data;

@Data
public class ServiceRequest {
    String name;
    Integer project_id;
    Integer order;
}
