package com.example.todoist.requestBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {
    Integer taskId;
    Integer projectId;
    String content;
    Object attachment;
}
