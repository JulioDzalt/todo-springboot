package com.example.todo_crud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    
    @GetMapping("/version")
    public String version() {
        return "1.1";
    }
}
