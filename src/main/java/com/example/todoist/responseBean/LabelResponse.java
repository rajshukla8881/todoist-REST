package com.example.todoist.responseBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LabelResponse {
    private int id;
    private String name;
    private int orders;
}