package com.example.todo_crud.utils;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;

public abstract class State {
    TodoModel todo;

    /**
     * Context passes itself through the state constructor. This may help a
     * state to fetch some useful context data if needed.
     */
    State() {}

    public abstract boolean change(TodoStatusE newTodoState);
}
