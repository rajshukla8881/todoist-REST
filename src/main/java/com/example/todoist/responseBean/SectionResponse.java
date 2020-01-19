package com.example.todoist.responseBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponse {
    private int id;
    private int project_id;
    private int orders;
    private String name;
}