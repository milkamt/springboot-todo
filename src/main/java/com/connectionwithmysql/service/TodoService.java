package com.connectionwithmysql.service;

import com.connectionwithmysql.model.Todo;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    List<Todo> getAllActive();
    List<Todo> getAllInactive();
    List<Todo> search(String field, String query);
    Todo getById(long id);
    void edit(long id, String title, boolean done, boolean urgent, String description,String assignee);
    void deleteById(long id);
    void addTodo(String title, LocalDateTime dueTime);
}
