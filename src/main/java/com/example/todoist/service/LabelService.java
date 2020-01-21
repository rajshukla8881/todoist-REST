package com.example.todoist.service;

import com.example.todoist.model.Label;
import com.example.todoist.responseBean.LabelResponse;

import java.util.List;

public interface LabelService {
    List<LabelResponse> getAllLabels();

    void saveLabel(Label label);

    LabelResponse getLabelById(Integer id);

    void deleteLabelById(Integer id);

    Label getOneLabelById(Integer id);

}