package com.example.todo_crud.models;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.todo_crud.validator.DateISOValidation;
import com.example.todo_crud.validator.EnumArrayValidator;
import com.example.todo_crud.validator.EnumValidator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TodoModel {
    
    private int id;

    @NotBlank(message = "Title is mandatory")
    @Value("${julio.title}")
    private String title;
    
    @NotBlank(message = "Content is mandatory")
    private String content;
    
    @NotNull(message = "The dateCreation is required.")
    @DateISOValidation(message = "The dateCreation is invalid.")
    private String dateCreation;
    
    @NotNull(message = "The status is required.")
    @EnumValidator(enumClazz = TodoStatusE.class , message = "The status is invalid")
    private String status;

    @EnumArrayValidator(enumClazz = TodoCategoriesE.class, message = "The categorias is invalid")
    private ArrayList<String> categorias;

}
