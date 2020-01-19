package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Section;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.repository.SectionRepository;
import com.example.todoist.requestBean.SectionRequest;
import com.example.todoist.responseBean.SectionResponse;
import com.example.todoist.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImplementer implements SectionService {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<SectionResponse> getAllSections() {
        List<Section> sectionList = sectionRepository.findAll();
        List<SectionResponse> sectionResponseList = new ArrayList<>();
        for (Section section : sectionList) {
            SectionResponse sectionResponse = SectionResponse.builder()
                    .id(section.getId())
                    .name(section.getName())
                    .orders(section.getOrders())
                    .project_id(section.getProject_id())
                    .build();
            sectionResponseList.add(sectionResponse);
        }
        return sectionResponseList;
    }


    @Override
    public SectionResponse createNewSection(SectionRequest sectionRequest) {
        Section section = new Section();
        if (sectionRequest.getName() == null || sectionRequest.getName().trim().length() == 0) {
            return null;
        }
        section.setName(sectionRequest.getName().trim());
        if (projectRepository.existsById(sectionRequest.getProject_id()))
            section.setProject_id(sectionRequest.getProject_id());
        else
            section.setProject_id(0);
        section.setOrders(sectionRequest.getOrder());
        sectionRepository.save(section);
        SectionResponse sectionResponse = SectionResponse.builder()
                .id(section.getId())
                .name(section.getName())
                .orders(section.getOrders())
                .project_id(section.getProject_id())
                .build();
        return sectionResponse;
    }


    @Override
    public SectionResponse getSection(int id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent()) {
            Section section = sectionRepository.getOne(id);
            SectionResponse sectionResponse = SectionResponse.builder()
                    .id(section.getId())
                    .name(section.getName())
                    .orders(section.getOrders())
                    .project_id(section.getProject_id())
                    .build();
            return sectionResponse;
        }
        return null;
    }


    @Override
    public int updateSection(int id, SectionRequest sectionRequest) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent()) {
            Section section = sectionRepository.getOne(id);
            if (sectionRequest.getName() == null || sectionRequest.getName().trim().length() == 0) {
                return -1;
            }
            section.setName(sectionRequest.getName().trim());
            if (projectRepository.existsById(sectionRequest.getProject_id()))
                section.setProject_id(sectionRequest.getProject_id());
            else
                section.setProject_id(0);
            section.setOrders(sectionRequest.getOrder());
            return (sectionRepository.save(section).getId());
        }
        return 0;
    }


    @Override
    public int deleteSection(int id) {
        Optional<Section> optionalSection = sectionRepository.findById(id);
        if (optionalSection.isPresent()) {
            sectionRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}