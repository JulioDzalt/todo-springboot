package com.example.todo_crud.controllers;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    TodoService todoService;
    
    @GetMapping("/version")
    public HashMap<String, ?> version() {
        return todoService.getConfig();
    }

    @GetMapping("/todo/")
    public List<TodoModel> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/todo/{id}")
    public TodoModel getTodobyId(@Valid @PathVariable int id) {
       return todoService.getTodobyId(id);
    }

    @PostMapping("/todo")
    public TodoModel createTodo(@Valid @RequestBody TodoModel todo) {
        System.out.println(todo);
        return todoService.insertTodo(todo);
    }

    @PutMapping("/todo")
    public TodoModel updateTodo(@Valid @RequestBody TodoModel todo) {
        return todoService.updateTodo(todo);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodo(@Valid @PathVariable int id) {
        return todoService.deleteTodoById(id);
    }
}
