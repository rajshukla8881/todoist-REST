package com.example.todoist.service;

import com.example.todoist.requestBean.LabelRequest;
import com.example.todoist.responseBean.LabelResponse;

import java.util.List;

public interface LabelService {

    List<LabelResponse> getAllLabels();

    LabelResponse createNewLabel(LabelRequest labelRequest);

    LabelResponse getLabel(int id);

    int updateLabel(int id, LabelRequest labelRequest);

    int deleteLabel(int id);
}