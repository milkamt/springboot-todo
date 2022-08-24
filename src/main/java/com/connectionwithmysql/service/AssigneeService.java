package com.connectionwithmysql.service;

import com.connectionwithmysql.model.Assignee;

import java.util.List;

public interface AssigneeService {
    List<Assignee> getAll();
    Assignee getById(long id);
    Assignee getByName(String name);
    void edit(Assignee assignee, String name);
    void delete(Assignee assignee);
    void addAssignee(String name, String email);
    void edit(long id, String name, String email);
}
