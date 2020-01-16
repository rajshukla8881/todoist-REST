package com.example.todoist.serviceImplementer;

import com.example.todoist.model.Section;
import com.example.todoist.repository.SectionRepository;
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

    @Override
    public List<SectionResponse> getAllSections() {
        List<Section> sectionList=sectionRepository.findAll();
        List<SectionResponse> sectionResponseList=new ArrayList<>();
        for(Section sectionListIterator:sectionList)
        {
            SectionResponse sectionResponse=new SectionResponse();
            sectionResponse.setId(sectionListIterator.getId());
            sectionResponse.setProject_id(sectionListIterator.getProjectId());
            sectionResponse.setOrder(sectionListIterator.getSectionOrder());
            sectionResponse.setName(sectionListIterator.getName());
            sectionResponseList.add(sectionResponse);

        }

        return sectionResponseList;
    }

    @Override
    public void saveSection(Section section) {
        sectionRepository.save(section);
    }

    @Override
    public SectionResponse getSectionById(Integer id) {
        Optional<Section> sectionOptional=sectionRepository.findById(id);
        if(sectionOptional.isPresent())
        {
            Section section=sectionOptional.get();
            SectionResponse sectionResponse=new SectionResponse();
            sectionResponse.setId(section.getId());
            sectionResponse.setProject_id(section.getProjectId());
            sectionResponse.setOrder(section.getSectionOrder());
            sectionResponse.setName(section.getName());

            return sectionResponse;
        }
        else
        {
            return new SectionResponse();
        }
    }

    @Override
    public void deleteSectionById(Integer id) {
        sectionRepository.deleteById(id);
    }

    @Override
    public Section getOneSectionById(Integer id) {
        return sectionRepository.getOne(id);
    }
}
