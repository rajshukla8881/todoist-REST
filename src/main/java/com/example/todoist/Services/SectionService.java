package com.example.todoist.Services;

import com.example.todoist.Models.Section;
import com.example.todoist.responseBean.SectionResponse;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    List<SectionResponse> getAllSections();
    void  saveSection(Section section);
    SectionResponse getSectionById(Integer id);
    void deleteSectionById(Integer id);
    Section getOneSectionById(Integer id);

}
