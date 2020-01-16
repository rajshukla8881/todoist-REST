package com.example.todoist.controller;

import com.example.todoist.model.Section;
import com.example.todoist.service.SectionService;
import com.example.todoist.requestBean.ServiceRequest;
import com.example.todoist.responseBean.SectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/v1")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @GetMapping("/sections")
    @ResponseBody
    private ResponseEntity getAllSections()
    {
        return new ResponseEntity(sectionService.getAllSections(), HttpStatus.OK);
    }

    @PostMapping("/sections")
    @ResponseBody
    private ResponseEntity createNewSection(@RequestBody ServiceRequest serviceRequest)
    {
        Section section;

        if(serviceRequest.getOrder()==null)
            section=new Section(serviceRequest.getName(),serviceRequest.getProject_id());
        else
            section=new Section(serviceRequest.getName(),serviceRequest.getProject_id(),serviceRequest.getOrder());
        sectionService.saveSection(section);
        SectionResponse sectionResponse=new SectionResponse();
        sectionResponse.setId(section.getId());
        sectionResponse.setProject_id(section.getProjectId());
        sectionResponse.setOrder(section.getSectionOrder());
        sectionResponse.setName(section.getName());
        return new ResponseEntity(sectionResponse,HttpStatus.OK);
    }

    @GetMapping("/sections/{id}")
    @ResponseBody
    private ResponseEntity getSingleSection(@PathVariable("id")Integer id)
    {
        return new ResponseEntity(sectionService.getSectionById(id),HttpStatus.OK);
    }

    @PostMapping("/sections/{id}")
    private ResponseEntity updateSection(@PathVariable("id")Integer id,@RequestBody ServiceRequest serviceRequest)
    {
        Section section=sectionService.getOneSectionById(id);
        SectionResponse sectionResponse=new SectionResponse();
        sectionResponse.setId(section.getId());
        sectionResponse.setProject_id(section.getProjectId());
        sectionResponse.setOrder(section.getSectionOrder());
        sectionResponse.setName(section.getName());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/sections/{id}")
    private ResponseEntity deleteSection(@PathVariable("id")Integer id)
    {

        sectionService.deleteSectionById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }




}
