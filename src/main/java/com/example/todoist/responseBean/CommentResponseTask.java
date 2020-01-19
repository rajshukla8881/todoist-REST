package com.example.todoist.responseBean;

import com.example.todoist.model.Attachment;
import lombok.Data;

@Data
public class CommentResponseTask {
    Integer id;
    Integer task_id;
    String content;
    String posted;
    AttachmentResponse attachment;
}
