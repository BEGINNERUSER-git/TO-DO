package com.example.todo_backend.model;


import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private String priority;
    private LocalDateTime dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // @ManyToOne
    // @JoinColumn(name = "owner_id")
    // private UserModel owner; // the creator/owner of the task

    // @ManyToOne
    // @JoinColumn(name = "assignee_id")
    // private UserModel assignee; // the person the task is assigned to (can be same as owner or team member)


    public enum TaskStatus{
        IN_PROGRESS,
        COMPLETED,
        OVERDUE
    }

  
}
