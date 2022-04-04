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
import com.example.todo_crud.utils.TodoState;
import com.example.todo_crud.utils.TodoStatesConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @Autowired
    TodoModel t;

    @Autowired
    TodoState todoState;

    @Autowired
    public TodoStatesConfig todoStatesConfig;
    
    @GetMapping("/version")
    public String version() {
        System.out.println(todoStatesConfig);
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



        t.setStatus(TodoStatusE.IN_PROGRESS.toString());
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
        //System.out.println(todo.getDateCreation());

        return todo;
    }

    @PutMapping("/todo")
    public TodoModel createTodoPut(@Valid @RequestBody TodoModel todo) {
        TodoModel todoA = new TodoModel();
        todoState.setTodoModel(todoA);
        
        // 1
        todoA.setStatus("TO_DO");
        todoState.change(TodoStatusE.CANCEL);
        todoState.change(TodoStatusE.DONE);
        todoState.change(TodoStatusE.IN_PROGRESS);
        todoState.change(TodoStatusE.TO_DO);
        
        // 2
        todoA.setStatus("TO_DO");
        todoState.change(TodoStatusE.IN_PROGRESS);
        todoState.change(TodoStatusE.DONE);
        todoState.change(TodoStatusE.TO_DO);


        // 1B
        todoA.setStatus("TO_DO");
        todoState.changeSimple(TodoStatusE.CANCEL);
        todoState.changeSimple(TodoStatusE.DONE);
        todoState.changeSimple(TodoStatusE.IN_PROGRESS);
        todoState.changeSimple(TodoStatusE.TO_DO);

        // 2B
        todoA.setStatus("TO_DO");
        todoState.changeSimple(TodoStatusE.IN_PROGRESS);
        todoState.changeSimple(TodoStatusE.DONE);
        todoState.changeSimple(TodoStatusE.TO_DO);




        return todo;
    }
}
