package com.arsen.services;

import com.arsen.enams.TaskStatus;
import com.arsen.models.Task;
import com.arsen.models.User;
import com.arsen.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Long newTask(User user, String header, String description, Date deadline, TaskStatus taskStatus) {
        Task task = new Task(user, header, description, deadline, taskStatus);
        taskRepository.save(task);
        return task.getId();
    }

    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public String deleteTaskById(Long id) {
        taskRepository.deleteById(id);
        return "Task id: " + id + " is deleted!";
    }

    public String updateTaskById(Long id, User user) {
        Task task = taskRepository.findById(id).orElse(null);
        if(task == null) return null;
        task.setOwner(user);
        return "Task id: " + id + " new username" + user.getName();
    }

}
