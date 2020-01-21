package com.example.todoist.responseBean;

import com.example.todoist.model.Attachment;
import lombok.Data;

@Data
public class CommentResponseProject {
    Integer id;
    Integer project_id;
    String content;
    String posted;
    AttachmentResponse attachment;
}
