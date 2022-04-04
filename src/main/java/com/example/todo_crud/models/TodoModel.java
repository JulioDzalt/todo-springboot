package com.example.todo_crud.models;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoModel {
    
    private int id;

    @NotBlank(message = "Title is mandatory")
    private String title;
    
    @NotBlank(message = "Content is mandatory")
    private String content;
    
    @NotNull(message = "The dateCreation is required.")
    @DateISOValidation(message = "The dateCreation is invalid.")
    private String dateCreation;
    
    private TodoStatusE status;

    private ArrayList<TodoCategoriesE> categorias;

}
