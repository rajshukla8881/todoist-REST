package com.example.todoist.requestBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequest {
    private int id;
    private int project_id;
    private int order;
    private String name;
}