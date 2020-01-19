package com.example.todoist.controller;

import com.example.todoist.requestBean.LabelRequest;
import com.example.todoist.responseBean.LabelResponse;
import com.example.todoist.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class LabelController {

    @Autowired
    LabelService labelService;

    @GetMapping("/labels")
    @ResponseBody
    public ResponseEntity<List<LabelResponse>> getAllLabelsList() {
        return new ResponseEntity(labelService.getAllLabels(), HttpStatus.OK);
    }

    @PostMapping("/labels")
    @ResponseBody
    public ResponseEntity<LabelResponse> labelCreation(@RequestBody LabelRequest labelRequest) {
        LabelResponse labelResponse = labelService.createNewLabel(labelRequest);
        if (labelResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(labelResponse, HttpStatus.OK);
    }

    @GetMapping("/labels/{id}")
    @ResponseBody
    public ResponseEntity<LabelResponse> getALabel(@PathVariable("id") int id) {
        LabelResponse labelResponse = labelService.getLabel(id);
        if (labelResponse == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(labelResponse, HttpStatus.OK);
    }

    @PostMapping("/labels/{id}")
    @ResponseBody
    public ResponseEntity<String> labelUpdation(@PathVariable("id") int id, @RequestBody LabelRequest labelRequest) {
        int obtainedId = labelService.updateLabel(id, labelRequest);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else if (obtainedId == -1)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/labels/{id}")
    @ResponseBody
    public ResponseEntity<String> labelDeletion(@PathVariable("id") int id) {
        int obtainedId = labelService.deleteLabel(id);
        if (obtainedId == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }
}