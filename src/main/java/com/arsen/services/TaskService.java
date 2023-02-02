package com.arsen.services;

import com.arsen.enams.TaskStatus;
import com.arsen.models.Task;
import com.arsen.models.User;
import com.arsen.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public long newUser(User user, String header, String description, Date deadline, TaskStatus taskStatus) {
        Task task = new Task(user, header, description, deadline, taskStatus);
        taskRepository.save(task);
        return task.getId();
    }


}
