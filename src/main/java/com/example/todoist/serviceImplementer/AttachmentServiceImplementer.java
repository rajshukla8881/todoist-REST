package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Attachment;
import com.example.todoist.repository.AttachmentRepository;
import com.example.todoist.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImplementer implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public void saveAttachment(Attachment attachment) {
        attachmentRepository.save(attachment);
    }
}