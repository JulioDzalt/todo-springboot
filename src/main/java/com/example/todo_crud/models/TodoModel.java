package com.example.todo_crud.models;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoModel {
    
    private int id;
    private String title;
    private String content;
    private Date dateCreation;
    private TodoStatusE status;
    private ArrayList<TodoCategoriesE> categorias;

}
