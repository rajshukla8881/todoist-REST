package com.example.todoist.responseBean;

import lombok.Data;

@Data
public class AttachmentResponse {
    String file_name;
    String file_type;
    String file_url;
    String resource_type;
}
