package com.example.todo_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_crud.models.TodoModel;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TodoRepository extends JpaRepository <TodoModel, Integer> {

}
