package com.connectionwithmysql.controller;

import com.connectionwithmysql.service.AssigneeService;
import com.connectionwithmysql.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AssigneeController {
    private final AssigneeService assigneeService;
    private final TodoService todoService;

    @GetMapping("/assignees")
    public String listAllAssignees(Model model) {
        model.addAttribute("assignees",assigneeService.getAll());
        return "assigneePage";
    }

    @GetMapping("/assignees/{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("name",assigneeService.getById(id).getName());
        model.addAttribute("email",assigneeService.getById(id).getEmail());
        return "assigneeEditForm";
    }

    @PostMapping("/assignees/{id}/edit")
    public String handleEditForm(@PathVariable long id,
                                 @RequestParam(name = "name")String name,
                                 @RequestParam(name = "email")String email,
                                 Model model) {
        model.addAttribute("id",id);
        assigneeService.edit(id,name,email);
        return "redirect:/assignees";
    }

    @GetMapping("/assignees/{id}/delete")
    public String delete(@PathVariable long id) {
        assigneeService.delete(assigneeService.getById(id));
        return "redirect:/assignees";
    }

    @GetMapping("/assignees/add")
    public String getNewAssigneeForm() {
        return "newAssigneeForm";
    }

    @PostMapping("/assignees/add")
    public String handleNewAssigneeForm(@RequestParam(name = "name") String name,
                                        @RequestParam(name = "email") String email) {
        assigneeService.addAssignee(name,email);
        return "redirect:/assignees";
    }

    @GetMapping("/assignees/show_todos/{id}")
    public String getTodos(@PathVariable long id, Model model) {
        model.addAttribute("assignee",assigneeService.getById(id));
        model.addAttribute("todos",assigneeService.getById(id).getTodos());
        return "viewTodos";
    }

    @GetMapping("/show_assignee/{id}")
    public String getAssignee(@PathVariable long id, Model model) {
        if (todoService.getById(id).getAssignee() == null) {
            return "noAssignee";
        } else {
            var x = todoService.getById(id).getAssignee().getId();
            return "redirect:/assignees/show_todos/" + x;
        }
    }
}