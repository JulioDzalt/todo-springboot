package com.example.todo_crud.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.Valid;

import com.example.todo_crud.models.TodoCategoriesE;
import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    TodoModel t;
    
    @GetMapping("/version")
    public String version() {
        return "1.1";
    }

    @GetMapping("/todo")
    public TodoModel getTodobyId() {
        
        t.setId(1);
        //t.setTitle("La de albert");
        t.setContent("hola mundo");
        
        // Input
        Date date = new Date(System.currentTimeMillis());

        // Conversion
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        //sdf.setTimeZone(TimeZone.getTimeZone("CST"));
        String text = sdf.format(date);
        t.setDateCreation(text);



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

    @PostMapping("/todo")
    public TodoModel createTodo(@Valid @RequestBody TodoModel todo) {
        System.out.println(todo);
        System.out.println(todo.getDateCreation());

        return todo;
    }
}
