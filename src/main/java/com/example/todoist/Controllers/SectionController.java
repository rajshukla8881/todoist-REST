package com.example.todoist.Controllers;

import com.example.todoist.Models.Section;
import com.example.todoist.Services.SectionService;
import com.example.todoist.requestBean.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

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
        Section section=new Section(serviceRequest.getName(),serviceRequest.getProject_id());
        sectionService.saveSection(section);
        return new ResponseEntity(section,HttpStatus.OK);
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
        Optional<Section> optionalSection=sectionService.getSectionById(id);
        if(optionalSection.isPresent())
        {
            Section section=optionalSection.get();
            section.setName(serviceRequest.getName());
            sectionService.saveSection(section);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            // No Section Found
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/sections/{id}")
    private ResponseEntity deleteSection(@PathVariable("id")Integer id)
    {
        Optional<Section> optionalSection=sectionService.getSectionById(id);
        if(optionalSection.isPresent())
        {
            sectionService.deleteSectionById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else
        {
            // No Section Found
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }




}
