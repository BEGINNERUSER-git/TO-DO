package com.example.todo_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_backend.model.TaskModel;
import com.example.todo_backend.model.TaskModel.TaskStatus;
import com.example.todo_backend.service.TaskService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
// Using "/api" as a base path in Spring controllers is a common convention to clearly separate RESTful API routes from other routes in the application. This improves clarity, maintainability, and routing management in the backend.
public class TaskController {
    @Autowired
    private TaskService task;



@GetMapping("/dashboard")
public ResponseEntity<List<TaskModel>> getAllTask(){
    return new ResponseEntity<>(task.getAllTasks(),HttpStatus.OK);
}

@GetMapping("/dashboard/{status}")
public ResponseEntity<?> getTaskByStatus(@PathVariable String status) {
    try {
        TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());
        List<TaskModel> tasks = task.getTaskByStatus(taskStatus);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>("Invalid task status: " + status, HttpStatus.BAD_REQUEST);
    }
}
    @PostMapping("/dashboard")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel tasked) {
        TaskModel createdTask =task.addTask(tasked);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/dashboard/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        task.toBeDeleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/dashboard/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskModel updateData) {
        try {
            TaskModel updatedTask = task.updateTask(id, updateData);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
}
