package com.example.todoist.Services;

import com.example.todoist.Models.Label;

import java.util.List;
import java.util.Optional;

public interface LabelService {
    List<Label> getAllLabels();

    void saveLabel(Label label);

    Optional<Label> getLabelById(Integer id);

    void deleteLabelById(Integer id);
}
