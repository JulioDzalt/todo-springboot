package com.example.todo_crud.utils;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;

public class TodoState extends State {

    public TodoState(TodoModel todo){
        super(todo);
    }

    @Override
    public boolean change(TodoStatusE newTodoState) {
        boolean wasChanged = false;
        String previousState = todo.getStatus();
        switch(newTodoState){
            case TO_DO :
                if (todo.getStatus().equals(TodoStatusE.TO_DO.toString()) ||
                    todo.getStatus().equals(TodoStatusE.IN_PROGRESS.toString())
                ) {
                    todo.setStatus(newTodoState.toString());
                    wasChanged = true;
                }
            break;
            
            
            case IN_PROGRESS:
                if (todo.getStatus().equals(TodoStatusE.TO_DO.toString()) ||
                    todo.getStatus().equals(TodoStatusE.IN_PROGRESS.toString()) ||
                    todo.getStatus().equals(TodoStatusE.DONE.toString())
                ) {
                    todo.setStatus(newTodoState.toString());
                    wasChanged = true;
                }
            break;
            
            case DONE :
                if (todo.getStatus().equals(TodoStatusE.IN_PROGRESS.toString()) ||
                    todo.getStatus().equals(TodoStatusE.DONE.toString())
                ) {
                    todo.setStatus(newTodoState.toString());
                    wasChanged = true;
                }
            break;

            case CANCEL:
                if( todo.getStatus().equals(TodoStatusE.TO_DO.toString()) ||
                    todo.getStatus().equals(TodoStatusE.IN_PROGRESS.toString()) ||
                    todo.getStatus().equals(TodoStatusE.CANCEL.toString())
                ){
                    todo.setStatus(newTodoState.toString());
                    wasChanged = true;
                }else{
                    System.out.println("ALSAd");
                    System.out.println(todo.getStatus());
                    System.out.println(TodoStatusE.TO_DO.toString());
                }
            break;

        }

        if(wasChanged){
            System.out.println(String.format("Todo status was changed from %s to %s", previousState, todo.getStatus()));
            
        }else{
            System.out.println(String.format("ERROR: Todo status can't changed from %s to %s", todo.getStatus(), newTodoState.toString()));
        }
        System.out.println(todo);
        return wasChanged;
    }
    
}
