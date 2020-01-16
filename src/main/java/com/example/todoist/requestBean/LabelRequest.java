package com.example.todoist.requestBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LabelRequest {
    private int id;
    private String name;
    private int order;
}