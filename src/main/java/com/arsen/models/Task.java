package com.arsen.models;

import com.arsen.enams.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_sequence")
    @SequenceGenerator(name = "tasks_sequence", sequenceName = "tasks_sequence", allocationSize = 1)
    private Long id;

    @NotEmpty(message = "Header не может быть пустым!")
    @Size(max = 20, message = "Превышено максимальное значение для поля header!")
    @Column(name = "header")
    private String header;

    @Size(max = 50, message = "Превышено максимальное значение для поля description!")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Deadline не может быть пустым!")
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task(User owner, String header, String description, LocalDateTime deadline, TaskStatus taskStatus) {
        this.owner = owner;
        this.header = header;
        this.description = description;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
    }


}
