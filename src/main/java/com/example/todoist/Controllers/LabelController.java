package com.example.todoist.Controllers;

import com.example.todoist.Models.Label;
import com.example.todoist.Models.Section;
import com.example.todoist.Services.LabelService;
import com.example.todoist.Services.SectionService;
import com.example.todoist.requestBean.LabelRequest;
import com.example.todoist.requestBean.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rest/v1")
public class LabelController {

    @Autowired
    LabelService labelService;

    @GetMapping("/labels")
    @ResponseBody
    private ResponseEntity getAllLabels()
    {
        return new ResponseEntity(labelService.getAllLabels(), HttpStatus.OK);
    }

    @PostMapping("/labels")
    @ResponseBody
    private ResponseEntity createNewLabel(@RequestBody LabelRequest labelRequest)
    {
        Label label=new Label(labelRequest.getName());
        labelService.saveLabel(label);
        return new ResponseEntity(label,HttpStatus.OK);
    }

    @GetMapping("/labels/{id}")
    @ResponseBody
    private ResponseEntity getSingleLabel(@PathVariable("id")Integer id)
    {
        return new ResponseEntity(labelService.getLabelById(id),HttpStatus.OK);
    }

    @PostMapping("/labels/{id}")
    private ResponseEntity updateLabel(@PathVariable("id")Integer id,@RequestBody LabelRequest labelRequest)
    {

            Label label=labelService.getOneLabelById(id);
            label.setName(labelRequest.getName());
            labelService.saveLabel(label);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


    @DeleteMapping("/labels/{id}")
    private ResponseEntity deleteLabel(@PathVariable("id")Integer id)
    {

            labelService.deleteLabelById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
