package com.example.todo_crud.convertes;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.example.todo_crud.models.TodoStatusE;

@Converter(autoApply = true)
public class TodoStatusConverter implements AttributeConverter<TodoStatusE, String> {

    @Override
    public String convertToDatabaseColumn(TodoStatusE status) {
        if (status == null) {
            return null;
        }
        return status.toString();
    }
                
    @Override
    public TodoStatusE convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TodoStatusE.values())
                .filter(c -> c.toString().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}