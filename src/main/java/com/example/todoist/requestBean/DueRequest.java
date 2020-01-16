package com.example.todoist.requestBean;

import lombok.*;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DueRequest {
    private String string;
    private String date;
    private String dateTime;
    private String timezone;
}