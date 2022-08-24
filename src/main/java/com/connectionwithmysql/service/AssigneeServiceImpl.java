package com.connectionwithmysql.service;

import com.connectionwithmysql.model.Assignee;
import com.connectionwithmysql.repository.AssigneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {
    private final AssigneeRepository assigneeRepository;

    @Override
    public List<Assignee> getAll() {
        return assigneeRepository.findAll();
    }

    @Override
    public void edit(Assignee assignee, String name) {
        assignee.setName(name);
    }

    @Override
    public void delete(Assignee assignee) {
        assigneeRepository.delete(assignee);
    }

    @Override
    public void addAssignee(String name, String email) {
        Assignee assignee = new Assignee();
        assignee.setName(name);
        assignee.setEmail(email);
        assigneeRepository.save(assignee);
    }

    @Override
    public void edit(long id, String name, String email) {
        var x = assigneeRepository.getById(id);
        x.setName(name);
        x.setEmail(email);
        assigneeRepository.save(x);
    }

    @Override
    public Assignee getById(long id) {
        return assigneeRepository.getById(id);
    }

    @Override
    public Assignee getByName(String name) {
        return assigneeRepository.getByName(name);
    }
}
