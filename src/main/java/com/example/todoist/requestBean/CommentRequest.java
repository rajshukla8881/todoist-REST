package com.example.todoist.requestBean;

import com.example.todoist.model.Attachment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {
    Integer task_id;
    Integer project_id;
    String content;
    AttachmentRequest attachment;
}
