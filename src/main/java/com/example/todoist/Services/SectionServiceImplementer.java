package com.example.todoist.Services;

import com.example.todoist.Models.Section;
import com.example.todoist.Repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SectionServiceImplementer implements SectionService {


    @Autowired
    SectionRepository sectionRepository;

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public void saveSection(Section section) {
        sectionRepository.save(section);
    }

    @Override
    public Optional<Section> getSectionById(Integer id) {
        return sectionRepository.findById(id);
    }

    @Override
    public void deleteSectionById(Integer id) {
        sectionRepository.deleteById(id);
    }
}
