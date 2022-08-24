package com.example.todo_crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import com.example.todo_crud.models.TodoModel;
import com.example.todo_crud.models.TodoStatusE;
import com.example.todo_crud.utils.TodoState;
import com.example.todo_crud.utils.TodoStatesConfig;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TodoCrudApplication.class)
class TodoStatesTests {

    @Autowired
    TodoStatesConfig todoStatesConfig;


    @Test
    @DisplayName("Update todo state correctly")
    void cambioDeEstadoCorrecto() {
        // Given
        int id = 1;

        TodoState todoState = new TodoState();
        todoState.todoStatesConfig = todoStatesConfig;
        System.out.println("Julio" + todoState.todoStatesConfig.getValidChanges() );
        TodoModel todo = new TodoModel(id, "A", "a", "2022-01-14T15:16:24Z", "TO_DO");
        String pastStatus = todo.getStatus();
        todoState.setTodoModel(todo);

        // When
        boolean result = todoState.changeSimple(TodoStatusE.IN_PROGRESS);
        
        // Then
        assertTrue(result);
        assertEquals(id, todo.getId());
        assertNotEquals(pastStatus, todo.getStatus());
        assertEquals(TodoStatusE.IN_PROGRESS.toString(), todo.getStatus());

    }

    @Test
    @DisplayName("Update todo state badly")
    void cambioDeEstadoIncorrecto() {
        // Given
        int id = 1;

        TodoState todoState = new TodoState();
        todoState.todoStatesConfig = todoStatesConfig;
        System.out.println("Julio" + todoState.todoStatesConfig.getValidChanges());
        TodoModel todo = new TodoModel(id, "A", "a", "2022-01-14T15:16:24Z", "CANCEL");
        String pastStatus = todo.getStatus();
        todoState.setTodoModel(todo);

        // When
        boolean result = todoState.changeSimple(TodoStatusE.DONE);

        // Then
        assertTrue(!result);
        assertEquals(id, todo.getId());
        assertEquals(pastStatus, todo.getStatus());
        assertEquals(TodoStatusE.CANCEL.toString(), todo.getStatus());

    }

    @Test
    @DisplayName("Update todo state badly")
    void cambioDeEstadoEstadosVacios() {
        // Given
        int id = 1;

        TodoState todoState = new TodoState();
        todoState.todoStatesConfig = new TodoStatesConfig();
        todoState.todoStatesConfig.validChanges = new HashMap<>();
        //System.out.println("Julio" + todoState.todoStatesConfig.getValidChanges());
        TodoModel todo = new TodoModel(id, "A", "a", "2022-01-14T15:16:24Z", "CANCEL");
        String pastStatus = todo.getStatus();
        todoState.setTodoModel(todo);

        // When
        boolean result = todoState.changeSimple(TodoStatusE.DONE);

        // Then
        assertTrue(!result);
        assertEquals(id, todo.getId());
        assertEquals(pastStatus, todo.getStatus());
        assertEquals(TodoStatusE.CANCEL.toString(), todo.getStatus());

    }


}
