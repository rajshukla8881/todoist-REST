package com.example.todoist.Services;

import com.example.todoist.Models.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    List<Section> getAllSections();
    void  saveSection(Section section);
    Optional<Section> getSectionById(Integer id);
    void deleteSectionById(Integer id);

}
