package com.example.todo_crud.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;
import com.example.todo_crud.repositories.TodoRepository;
import com.example.todo_crud.utils.TodoState;
import com.example.todo_crud.utils.TodoStatesConfig;
import com.example.todo_crud.exception.ApiRequestException;
import com.example.todo_crud.models.TodoCategoriesE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    
    @Autowired
    TodoState todoState;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    public TodoStatesConfig todoStatesConfig;

    public HashMap<String, Object> getConfig() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("defaultState", todoStatesConfig.defaultState);
        config.put("states", todoStatesConfig.states);
        config.put("validChanges", todoStatesConfig.validChanges);
        return config;
    }

    public List<TodoModel> getTodos(){
        return todoRepository.findAll();
    }

    public TodoModel insertTodo(TodoModel todo) {
        if(todo.getId() != 0 ){
            if (todoRepository.existsById(todo.getId())){
                System.out.println("Ya existe");
                throw new ApiRequestException("You must not define de id on the request", HttpStatus.BAD_REQUEST);
                
            }else{
                System.out.println("No existe, se crea");
            }
        }
        return todoRepository.save(todo);
    }
    
    public TodoModel getTodobyId(int id) {

        Optional<TodoModel> todo = todoRepository.findById(id);
        if (todo.isPresent())
            return todo.get();
        else{
            return new TodoModel();
        }
        // TodoModel t = new TodoModel();
        // t.setId(1);
        // // t.setTitle("La de albert");
        // t.setContent("hola mundo");

        // // Input
        // Date date = new Date(System.currentTimeMillis());

        // // Conversion
        // SimpleDateFormat sdf;
        // sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        // sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        // // sdf.setTimeZone(TimeZone.getTimeZone("CST"));
        // String text = sdf.format(date);
        // t.setDateCreation(text);

        // t.setStatus(TodoStatusE.IN_PROGRESS);
        // // t.setCategorias(
        // //         new ArrayList<String>() {
        // //             {
        // //                 add(TodoCategoriesE.Home.toString());
        // //                 add(TodoCategoriesE.PersonalBelongings.toString());
        // //             }
        // //         });

        // return t;
    }

    public TodoModel updateTodo(TodoModel todo) {
        TodoModel todoNew = null;
        Optional<TodoModel> todoFound = todoRepository.findById(todo.getId());
        if (todoFound.isPresent()){
            todoNew = todoFound.get();
    
            //Valid changes
            todoState.setTodoModel(todoNew);

            TodoStatusE curretStatus = TodoStatusE.valueOf(todo.getStatus());
            
            //Change if is posible
            if(curretStatus != null){
                if(todoState.changeSimple(curretStatus)){
                    todoRepository.save(todo);
                }
            }

        }
        
        return todoNew;
        

        // // 1
        // todoA.setStatus("TO_DO");
        // todoState.change(TodoStatusE.CANCEL);
        // todoState.change(TodoStatusE.DONE);
        // todoState.change(TodoStatusE.IN_PROGRESS);
        // todoState.change(TodoStatusE.TO_DO);

        // // 2
        // todoA.setStatus("TO_DO");
        // todoState.change(TodoStatusE.IN_PROGRESS);
        // todoState.change(TodoStatusE.DONE);
        // todoState.change(TodoStatusE.TO_DO);

        // // 1B
        // todoA.setStatus("TO_DO");
        // todoState.changeSimple(TodoStatusE.CANCEL);
        // todoState.changeSimple(TodoStatusE.DONE);
        // todoState.changeSimple(TodoStatusE.IN_PROGRESS);
        // todoState.changeSimple(TodoStatusE.TO_DO);

        // // 2B
        // todoA.setStatus("TO_DO");
        // todoState.changeSimple(TodoStatusE.IN_PROGRESS);
        // todoState.changeSimple(TodoStatusE.DONE);
        // todoState.changeSimple(TodoStatusE.TO_DO);

        // return todo;
    }

    public boolean deleteTodoById(int id) {
        TodoModel todoFound = todoRepository.getById(id);
        if(todoFound != null){
            todoRepository.delete(todoFound);
            return true;
        }
        return false;
    }
}
