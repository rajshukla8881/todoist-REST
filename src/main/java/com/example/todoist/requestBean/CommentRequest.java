package com.example.todoist.requestBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {
    Integer task_id;
    Integer project_id;
    String content;
    Object attachment;
}
