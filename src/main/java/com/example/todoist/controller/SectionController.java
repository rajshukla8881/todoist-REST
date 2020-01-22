package com.example.todoist.controller;

import com.example.todoist.model.Section;
import com.example.todoist.repository.ProjectRepository;
import com.example.todoist.requestBean.ServiceRequest;
import com.example.todoist.responseBean.SectionResponse;
import com.example.todoist.service.ProjectService;
import com.example.todoist.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    boolean checkValidSectionInput(String sectionName, Integer projectId) {
        if (sectionName == null || sectionName.trim().length() == 0 || projectId == null) {
            return false;
        }
        return true;
    }


    boolean checkValidSectionInputName(String sectionName) {
        if (sectionName == null || sectionName.trim().length() == 0 ) {
            return false;
        }
        return true;
    }

    @GetMapping("/sections")
    @ResponseBody
    private ResponseEntity getAllSections() {
        return new ResponseEntity(sectionService.getAllSections(), HttpStatus.OK);
    }

    @PostMapping("/sections")
    @ResponseBody
    private ResponseEntity createNewSection(@RequestBody ServiceRequest serviceRequest) {

        //Check if the Section Input is valid or Not
        if (!checkValidSectionInput(serviceRequest.getName(), serviceRequest.getProject_id())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Section section;
        String serviceRequestName = serviceRequest.getName().trim();

        if (serviceRequest.getOrder() == null) {

            Integer id = serviceRequest.getProject_id();
            if (projectRepository.existsById(id))
                section = new Section(serviceRequestName, serviceRequest.getProject_id());
            else
                section = new Section(serviceRequestName, 0);
        } else {
            Integer id = serviceRequest.getProject_id();
            if (projectRepository.existsById(id))
                section = new Section(serviceRequestName, serviceRequest.getProject_id(), serviceRequest.getOrder());
            else
                section = new Section(serviceRequestName, 0, serviceRequest.getOrder());
        }
        sectionService.saveSection(section);
        SectionResponse sectionResponse = new SectionResponse();
        sectionResponse.setId(section.getId());
        sectionResponse.setProject_id(section.getProjectId());
        sectionResponse.setOrder(section.getSectionOrder());
        sectionResponse.setName(section.getName());
        return new ResponseEntity(sectionResponse, HttpStatus.OK);
    }

    @GetMapping("/sections/{id}")
    @ResponseBody
    private ResponseEntity getSingleSection(@PathVariable("id") Integer id) {
        if (sectionService.getSectionById(id).getId() != null)
            return new ResponseEntity(sectionService.getSectionById(id), HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/sections/{id}")
    private ResponseEntity updateSection(@PathVariable("id") Integer id, @RequestBody ServiceRequest serviceRequest) {
        if (!checkValidSectionInputName(serviceRequest.getName())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (sectionService.getSectionById(id).getId() != null) {
            Section section = sectionService.getOneSectionById(id);
            SectionResponse sectionResponse = new SectionResponse();
            sectionResponse.setId(section.getId());
            sectionResponse.setProject_id(section.getProjectId());
            sectionResponse.setOrder(section.getSectionOrder());
            sectionResponse.setName(section.getName().trim());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/sections/{id}")
    private ResponseEntity deleteSection(@PathVariable("id") Integer id) {
        if (sectionService.getSectionById(id).getId() != null) {
            sectionService.deleteSectionById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}