package com.example.todo_crud;


import java.util.Arrays;
import java.util.List;

import com.example.todo_crud.models.TodoModel;

public class DatosTodo {
    public final static List<TodoModel> TODOS = Arrays.asList(
            new TodoModel(1,"A", "a", "2022-01-14T15:16:24Z", "DONE"),
            new TodoModel(2,"B", "b", "2022-01-14T15:16:24Z", "DONE"),
            new TodoModel(3,"C", "c", "2022-01-14T15:16:24Z", "DONE")
    );

}
