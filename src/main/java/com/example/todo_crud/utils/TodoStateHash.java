package com.example.todo_crud.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
public class TodoStateHash extends State {

    //@Value("${todo.states}")
    //HashMap<String, List<String>> states = new HashMap<String, List<String>>();
    
    @Value("#{${todo.states}}")
    Map<?, ?> states;
    
    
    //@Value("${todo.states}")
    //List<String> states = new ArrayList<>();

    public TodoStateHash(TodoModel todo, Map<String, List<String>> states){
        super(todo);
        this.states = states;
    }

    public void setTodoModel(TodoModel todo){
        this.todo=todo;
    }

    @Override
    public boolean change(TodoStatusE newTodoState) {
        System.out.println(states.get("TO_DO"));
        System.out.println(states.get("TO_DO").getClass());
        System.out.println(states.get("TO_DO2"));
        System.out.println("Hola"+ states );
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
