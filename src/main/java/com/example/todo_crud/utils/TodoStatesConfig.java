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

    
    public String defaultState;
    public ArrayList<String> states;
    public HashMap<String, ArrayList<String>> validChanges;
    
}
