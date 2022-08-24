package com.example.todo_crud.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;
import com.example.todo_crud.repositories.TodoRepository;
import com.example.todo_crud.utils.TodoState;
import com.example.todo_crud.utils.TodoStatesConfig;
import com.example.todo_crud.exception.ApiRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try{
            if(todo.getId() != 0 ){
                if (todoRepository.existsById(todo.getId())){
                    System.out.println("Ya existe");
                    throw new ApiRequestException("You must not define de id on the request", HttpStatus.BAD_REQUEST);
                    
                }else{
                    System.out.println("No existe, se crea");
                }
            }
            return todoRepository.save(todo);
        }catch(Exception e){
            throw new ApiRequestException("DB exception", e);
        }
        
    }
    
    public TodoModel getTodobyId(int id) {

        Optional<TodoModel> todo = todoRepository.findById(id);
        if (todo.isPresent()){
            return todo.get();
        }
        else{
            throw new ApiRequestException("Not found", HttpStatus.NOT_FOUND);
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
        try{
            if (todo.getId() != 0 ){

                TodoModel todoToUpdate = null;
                Optional<TodoModel> todoFound = todoRepository.findById(todo.getId());
                
                if (todoFound.isPresent()){
                    todoToUpdate = todoFound.get();
            
                    //Valid changes
                    todoState.setTodoModel(todoToUpdate);

                    TodoStatusE curretStatus = TodoStatusE.valueOf(todo.getStatus());
                    
                    //Change if is posible
                    if(curretStatus != null){
                        if(todoState.changeSimple(curretStatus)){
                            todoRepository.save(todo);
                        }
                        else {
                            throw new ApiRequestException("Change of status not valid", HttpStatus.BAD_REQUEST);
                        }
                    } 

                }
                return todoToUpdate;
            } else{
                throw new ApiRequestException("You must especific the id", HttpStatus.BAD_REQUEST);
            }
            

        } catch (ApiRequestException e) {
            throw e;
        } catch( Exception e) {
            System.out.println(e);
            throw new ApiRequestException("Error to conect DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        

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

    public ResponseEntity<?> deleteTodoById(int id) {
       
        try {
            TodoModel todoFound = todoRepository.getById(id);
            if (todoFound != null) {
                todoRepository.delete(todoFound);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            throw new RuntimeException("Error parsing date", e);
        }
    }
}
