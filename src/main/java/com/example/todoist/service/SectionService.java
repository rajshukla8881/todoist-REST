package com.example.todoist.service;

import com.example.todoist.model.Section;
import com.example.todoist.responseBean.SectionResponse;

import java.util.List;

public interface SectionService {

    List<SectionResponse> getAllSections();
    void  saveSection(Section section);
    SectionResponse getSectionById(Integer id);
    void deleteSectionById(Integer id);
    Section getOneSectionById(Integer id);

}
