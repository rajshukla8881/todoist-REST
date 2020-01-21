package com.example.todoist.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@CrossOrigin
@RestController
@RequestMapping("/rest/v1")
public class SampleController {

    @GetMapping("/hello")
    public String sampleMapping() {
        return "Hello World!";
    }
}
