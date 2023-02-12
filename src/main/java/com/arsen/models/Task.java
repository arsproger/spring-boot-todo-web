package com.arsen.models;

import com.arsen.enams.TaskStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task(User owner, String header, String description, Date deadline, TaskStatus taskStatus) {
        this.owner = owner;
        this.header = header;
        this.description = description;
        this.deadline = deadline;
        this.taskStatus = taskStatus;
    }


}
