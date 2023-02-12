package com.arsen.controllers;

import com.arsen.models.Task;
import com.arsen.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("new/{userId}")
    public String newTask(@ModelAttribute("task") Task task, @PathVariable("userId") long id) {
        return "task-new";
    }

    @PostMapping("/save/{owner}")
    public String create(@ModelAttribute Task task, @PathVariable long owner) {
        service.newTask(owner, task);
        return "redirect:/user/" + owner;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        long userId = service.getById(id).getOwner().getId();
        service.deleteTaskById(id);
        return "redirect:/user/" + userId;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("task", service.getById(id));
        return "task-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("task") Task task, @PathVariable("id") Long id) {
        service.updateTaskById(id, task);
        return "redirect:/user/" + service.getById(id).getOwner().getId();
    }

}
