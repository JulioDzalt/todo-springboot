package com.example.todo_crud.utils;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "todo")
@Data
public class TodoStatesConfig {

    
    public HashMap<String, ArrayList<String>> states;

    public TodoStatesConfig(HashMap<String, ArrayList<String>> states){
        this.states = states;
    }
    
}
