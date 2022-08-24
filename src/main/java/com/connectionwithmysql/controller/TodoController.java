package com.connectionwithmysql.controller;

import com.connectionwithmysql.service.AssigneeService;
import com.connectionwithmysql.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class TodoController {
    private final TodoService todoService;
    private final AssigneeService assigneeService;

    @RequestMapping({"/", "/list"})
    public String list(Model model,
                       @RequestParam(value = "isActive",required = false) String active) {

        var todos = todoService.getAllTodos();
        if (!Objects.isNull(active)) {
            if (active.equals("true")) {
                todos = todoService.getAllActive();
            }
            if (active.equals("false")) {
                todos = todoService.getAllInactive();
            }
            else {
                todos = todoService.getAllTodos();
            }
        }
        model.addAttribute("todos",todos);
        return "index";
    }

    @GetMapping("/{id}/detail")
    public String getDetail(@PathVariable long id,Model model) {
        model.addAttribute("todo", todoService.getById(id));

        if (todoService.getById(id).getDetail() != null) {
            model.addAttribute("detail",
                    todoService.getById(id).getDetail().getDescription());
        } else {
            model.addAttribute("detail","no detail record added");
        }
        return "detail";
    }

    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(name = "query") String query,
                         @RequestParam(name = "field") String field) {
        model.addAttribute("todos",todoService.search(field,query));
        return "index";
    }

    @GetMapping("/add")
    public String getNewTaskForm() {
        return "newTaskForm";
    }

    @PostMapping("/add")
    public String handleNewTaskForm(@RequestParam(name = "newTask") String task,
                                    @RequestParam(name = "dueTime") String dueTime) {
        todoService.addTodo(task, LocalDateTime.parse(dueTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id) {
        todoService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("title",todoService.getById(id).getTitle()); // for title input default value

        if (todoService.getById(id).getDetail() != null) {                          // for description input default value
            model.addAttribute("description",todoService.getById(id).getDetail().getDescription());
        } else {
            model.addAttribute("description", "no detail record added");
        }

        model.addAttribute("assignees",assigneeService.getAll());
        return "editForm";
    }

    @PostMapping("/{id}/edit")
    public String handleEditForm(@PathVariable long id,
                                 @RequestParam(name = "title") String title,
                                 @RequestParam(name = "urgent",required = false, defaultValue = "false") Boolean urgent,
                                 @RequestParam(name = "done",required = false, defaultValue = "false") Boolean done,
                                 @RequestParam(name = "description",required = false,defaultValue = "") String description,
                                 @RequestParam(name = "assignee") String assignee,
                                 @RequestParam(name = "dueTime", required = false) String dueTime,
                                 Model model) {
        model.addAttribute("id",id);

        if (dueTime != null) {
            todoService.getById(id).setDueDate(
                    LocalDateTime.parse(dueTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        todoService.edit(id,title,done,urgent,description,assignee);
        return "redirect:/";
    }
}
