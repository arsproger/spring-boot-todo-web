package com.arsen.services;

import com.arsen.enams.TaskStatus;
import com.arsen.models.Task;
import com.arsen.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public void newTask(long id, Task task) {
        task.setOwner(userService.getById(id));
        task.setTaskStatus(TaskStatus.NEW);
        taskRepository.save(task);
    }

    public Task getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public void updateTaskById(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) return;
        task.setHeader(updatedTask.getHeader());
        task.setDescription(updatedTask.getDescription());
        task.setDeadline(updatedTask.getDeadline());
        task.setTaskStatus(updatedTask.getTaskStatus());

        taskRepository.save(task);
    }

}
