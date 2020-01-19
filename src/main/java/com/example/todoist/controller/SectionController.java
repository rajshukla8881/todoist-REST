package com.example.todoist.controller;

import com.example.todoist.requestBean.SectionRequest;
import com.example.todoist.responseBean.SectionResponse;
import com.example.todoist.service.ProjectService;
import com.example.todoist.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/sections")
    @ResponseBody
    public ResponseEntity<List<SectionResponse>> getAllSectionsList() {
        return new ResponseEntity<>(sectionService.getAllSections(), HttpStatus.OK);
    }

    @PostMapping("/sections")
    @ResponseBody
    public ResponseEntity<SectionResponse> sectionCreation(@RequestBody SectionRequest sectionRequest) {
        SectionResponse sectionResponse = sectionService.createNewSection(sectionRequest);
        if (sectionResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(sectionResponse, HttpStatus.OK);
    }

    @GetMapping("/sections/{id}")
    @ResponseBody
    public ResponseEntity<SectionResponse> getASection(@PathVariable("id") int id) {
        SectionResponse sectionResponse = sectionService.getSection(id);
        if (sectionResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(sectionResponse, HttpStatus.OK);
    }

    @PostMapping("/sections/{id}")
    @ResponseBody
    public ResponseEntity<String> sectionUpdation(@PathVariable("id") int id, @RequestBody SectionRequest sectionRequest) {
        int obtainedId = sectionService.updateSection(id, sectionRequest);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (obtainedId == -1)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/sections/{id}")
    @ResponseBody
    public ResponseEntity<String> sectionDeletion(@PathVariable("id") int id) {
        int obtainedId = sectionService.deleteSection(id);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}