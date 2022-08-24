package com.connectionwithmysql.service;

import com.connectionwithmysql.model.Detail;
import com.connectionwithmysql.model.Todo;
import com.connectionwithmysql.repository.DetailRepository;
import com.connectionwithmysql.repository.TodoRepository;
import com.connectionwithmysql.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final DetailRepository detailRepository;
    private final AssigneeService assigneeService;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> getAllActive() {
        return todoRepository.findAllByDoneFalse();
    }

    @Override
    public List<Todo> getAllInactive() {
        return todoRepository.findAllByDoneTrue();
    }

    @Override
    public Todo getById(long id) {
        return todoRepository.getById(id);
    }

    @Override
    public void edit(long id, String title, boolean done, boolean urgent,
                     String description,String assignee) {
        var x = todoRepository.getById(id);
        x.setTitle(title);
        x.setDone(done);
        x.setUrgent(urgent);

        if (x.getDetail() == null) {
            Detail detail = new Detail();
            detail.setTodo(x);
            x.setDetail(detail);
            detailRepository.save(detail);
            x.getDetail().setDescription(description);
        }
        x.getDetail().setDescription(description);
        if (!assignee.equals("none")) {
            x.setAssignee(assigneeService.getByName(assignee));
            assigneeService.getByName(assignee).getTodos().add(x);
        }
        if (assignee.equals("none")) {
            x.setAssignee(null);
        }
        todoRepository.save(x);
    }

    @Override
    public void deleteById(long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void addTodo(String title, LocalDateTime dueTime) {
        Todo todo = new Todo();
        Detail detail = new Detail();
        todo.setTitle(title);
        todo.setDueDate(dueTime);
        detail.setTodo(todo);
        todoRepository.save(todo);
        detailRepository.save(detail);
    }

    @Override
    public List<Todo> search(String field, String query) {
        if (field.equals("title")) {
            return todoRepository.findAll().stream()
                    .filter(f -> f.getTitle().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (field.equals("description")) {
            return todoRepository.findAll().stream()
                    .filter(x -> Objects.nonNull(x.getDetail()))
                    .filter(x -> x.getDetail().getDescription().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (field.equals("assignee")) {
            return todoRepository.findAll().stream()
                    .filter(x -> Objects.nonNull(x.getAssignee()))
                    .filter(x -> x.getAssignee().getName().toLowerCase().equals(query.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}