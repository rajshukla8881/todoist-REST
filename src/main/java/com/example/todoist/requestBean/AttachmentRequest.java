package com.example.todoist.requestBean;

import lombok.Data;

@Data
public class AttachmentRequest {
    String file_name;
    String file_type;
    String file_url;
    String resource_type;
}