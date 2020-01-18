package com.example.todoist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/rest/v1/")
public class UserController {
    @GetMapping("/user")
    public Principal getUserDetails(Principal principal)
    {

        return principal;
    }
}
