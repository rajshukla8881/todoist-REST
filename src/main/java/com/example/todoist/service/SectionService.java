package com.example.todoist.service;

import com.example.todoist.requestBean.SectionRequest;
import com.example.todoist.responseBean.SectionResponse;

import java.util.List;

public interface SectionService {

    List<SectionResponse> getAllSections();

    SectionResponse createNewSection(SectionRequest sectionRequest);

    SectionResponse getSection(int id);

    int updateSection(int id, SectionRequest sectionRequest);

    int deleteSection(int id);

}