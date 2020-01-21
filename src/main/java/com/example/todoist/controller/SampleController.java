package com.example.todoist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1")
public class SampleController {

    @GetMapping("/hello")
    public String sampleMapping() {
        return "Hello World!";
    }
}