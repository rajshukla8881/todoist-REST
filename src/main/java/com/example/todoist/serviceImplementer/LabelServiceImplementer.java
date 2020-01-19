package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Label;
import com.example.todoist.repository.LabelRepository;
import com.example.todoist.requestBean.LabelRequest;
import com.example.todoist.responseBean.LabelResponse;
import com.example.todoist.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LabelServiceImplementer implements LabelService {

    @Autowired
    LabelRepository labelRepository;

    @Override
    public List<LabelResponse> getAllLabels() {
        List<Label> labelList = labelRepository.findAll();
        List<LabelResponse> labelResponseList = new ArrayList<>();
        for (Label label : labelList) {
            LabelResponse labelResponse = LabelResponse.builder()
                    .id(label.getId())
                    .name(label.getName())
                    .orders(label.getOrders())
                    .build();
            labelResponseList.add(labelResponse);
        }
        return labelResponseList;
    }


    @Override
    public LabelResponse createNewLabel(LabelRequest labelRequest) {
        Label label = new Label();
        if (labelRequest.getName() == null || labelRequest.getName().trim().length() == 0) {
            return null;
        }
        label.setName(labelRequest.getName().trim());
        label.setOrders(labelRequest.getOrders());
        labelRepository.save(label);
        LabelResponse labelResponse = LabelResponse.builder()
                .id(label.getId())
                .name(label.getName())
                .orders(label.getOrders())
                .build();
        return labelResponse;
    }


    @Override
    public LabelResponse getLabel(int id) {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        if (optionalLabel.isPresent()) {
            Label label = labelRepository.getOne(id);
            LabelResponse labelResponse = LabelResponse.builder()
                    .id(label.getId())
                    .name(label.getName())
                    .orders(label.getOrders())
                    .build();
            return labelResponse;
        }
        return null;
    }


    @Override
    public int updateLabel(int id, LabelRequest labelRequest) {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        if (optionalLabel.isPresent()) {
            Label label = labelRepository.getOne(id);
            if (labelRequest.getName() == null || labelRequest.getName().trim().length() == 0) {
                return -1;
            }
            label.setName(labelRequest.getName().trim());
            label.setOrders(labelRequest.getOrders());
            return labelRepository.save(label).getId();
        }
        return 0;
    }


    @Override
    public int deleteLabel(int id) {
        Optional<Label> optionalLabel = labelRepository.findById(id);
        if (optionalLabel.isPresent()) {
            labelRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}