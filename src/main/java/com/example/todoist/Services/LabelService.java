package com.example.todoist.Services;

import com.example.todoist.Models.Label;
import com.example.todoist.Models.Section;
import com.example.todoist.responseBean.LabelResponse;

import java.util.List;
import java.util.Optional;

public interface LabelService {
    List<LabelResponse> getAllLabels();

    void saveLabel(Label label);

    LabelResponse getLabelById(Integer id);

    void deleteLabelById(Integer id);

    Label getOneLabelById(Integer id);

}
