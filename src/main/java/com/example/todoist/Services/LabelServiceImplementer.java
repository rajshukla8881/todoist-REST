package com.example.todoist.Services;

import com.example.todoist.Models.Label;
import com.example.todoist.Repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelServiceImplementer implements LabelService {

    @Autowired
    LabelRepository labelRepository;

    @Override
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @Override
    public void saveLabel(Label label) {
        labelRepository.save(label);
    }

    @Override
    public Optional<Label> getLabelById(Integer id) {
        return labelRepository.findById(id);
    }

    @Override
    public void deleteLabelById(Integer id) {
        labelRepository.deleteById(id);
    }
}
