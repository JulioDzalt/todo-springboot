package com.example.todo_crud.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.todo_crud.validator.DateISOValidation;
import com.example.todo_crud.validator.EnumValidator;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todos") 
public class TodoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title is mandatory")
    @Value("${julio.title}")
    private String title;
    
    @NotBlank(message = "Content is mandatory")
    private String content;
    
    @Column(name="datecreation")
    @NotNull(message = "The dateCreation is required.")
    @DateISOValidation(message = "The dateCreation is invalid.")
    private String dateCreation;
    
    @NotBlank(message = "The status is required.")
    @EnumValidator(enumClazz = TodoStatusE.class, message = "The status is invalid")
    //@Convert(converter = TodoStatusConverter.class)
    //@Column(columnDefinition = "ENUM('DONE', 'IN_PROGRESS', 'TO_DO', 'CANCEL')")
    //@Enumerated(EnumType.STRING)
    private String status;

    // @EnumArrayValidator(enumClazz = TodoCategoriesE.class, message = "The categorias is invalid")
    // private ArrayList<String> categorias;

}
