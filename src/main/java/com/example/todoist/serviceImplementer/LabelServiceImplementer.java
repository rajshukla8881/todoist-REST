package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Label;
import com.example.todoist.repository.LabelRepository;
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
        List<Label> labelList=labelRepository.findAll();
        List<LabelResponse> labelResponseList=new ArrayList<>();
        for(Label labelListIterator:labelList)
        {
            LabelResponse labelResponse=new LabelResponse();
            labelResponse.setId(labelListIterator.getId());
            labelResponse.setName(labelListIterator.getName());
            labelResponse.setOrder(labelListIterator.getLabelOrder());

            labelResponseList.add(labelResponse);

        }

        return labelResponseList;
    }

    @Override
    public void saveLabel(Label label) {
        labelRepository.save(label);
    }

    @Override
    public LabelResponse getLabelById(Integer id) {
        Optional<Label> labelOptional=labelRepository.findById(id);
        if(labelOptional.isPresent())
        {
            Label label=labelOptional.get();
            LabelResponse labelResponse=new LabelResponse();
            labelResponse.setId(label.getId());
            labelResponse.setName(label.getName());
            labelResponse.setOrder(label.getLabelOrder());

            return labelResponse;
        }
        else
        {
            return new LabelResponse();
        }
    }

    @Override
    public void deleteLabelById(Integer id) {
        labelRepository.deleteById(id);
    }

    @Override
    public Label getOneLabelById(Integer id) {
        return labelRepository.getOne(id);
    }
}
