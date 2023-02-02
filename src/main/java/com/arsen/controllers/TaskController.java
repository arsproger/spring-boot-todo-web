package com.arsen.controllers;

import com.arsen.enams.TaskStatus;
import com.arsen.models.Task;
import com.arsen.models.User;
import com.arsen.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    TaskService service;

    @GetMapping("/get/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Task> getAllTask() {
        return service.getAllTask();
    }

    @PostMapping("/save")
    public Long saveTask(@RequestParam User user,
                         @RequestParam String header,
                         @RequestParam String description,
                         @RequestParam Date deadline,
                         @RequestParam TaskStatus taskStatus) {
        return service.newTask(user, header, description, deadline, taskStatus);
    }

//    @PostMapping("/save")
//    public Long saveTask(@RequestBody Task task) {
//        return service.newTaskPro(task);
//    }

    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        return service.deleteTaskById(id);
    }

    @PatchMapping("/update")
    public String updateTaskById(@RequestParam Long id,
                                 @RequestParam User user) {
        return service.updateTaskById(id, user);
    }
}
