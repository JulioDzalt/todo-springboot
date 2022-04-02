package com.example.todo_crud.controllers;

import java.util.ArrayList;
import java.util.Date;

import com.example.todo_crud.models.TodoCategoriesE;
import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    
    @GetMapping("/version")
    public String version() {
        return "1.1";
    }

    @GetMapping("/todo")
    public TodoModel getTodobyId() {
        TodoModel t = new TodoModel();
        t.setId(1);
        t.setTitle("La de albert");
        t.setContent("hola mundo");
        t.setDateCreation(new Date());
        t.setStatus(TodoStatusE.IN_PROGRESS);
        t.setCategorias(
            new ArrayList<TodoCategoriesE>() {
                {
                    add(TodoCategoriesE.Home); 
                    add(TodoCategoriesE.PersonalBelongings);
                }
            }
        );
        
        return  t;
    }
}
