package com.example.todoist.responseBean;

import com.example.todoist.model.Attachment;
import lombok.Data;

@Data
public class CommentResponse {
    Integer id;
    Integer task_id;
    Integer project_id;
    String content;
    String posted;
    Attachment attachment;
}