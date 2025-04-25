package com.example.todo_backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_backend.model.TaskModel;
import com.example.todo_backend.model.TaskModel.TaskStatus;
import com.example.todo_backend.repository.Taskrepo;
import org.springframework.web.bind.annotation.ControllerAdvice;



@ControllerAdvice
@Service
public class TaskService {
   
    @Autowired
    private Taskrepo task;
    



    public List<TaskModel> getAllTasks(){
        return task.findAll();
    }

    public List<TaskModel> getTaskByStatus(TaskStatus taskStatus) {
        return task.findByStatus(taskStatus);
    }



    public TaskModel addTask(TaskModel tasked){
        if (tasked.getStatus() == null) {
            tasked.setStatus(TaskStatus.OVERDUE);
        }
       return task.save(tasked);
        
    }

    

    

     

    public TaskModel updateTask(Long id, TaskModel updatedTask) {
        return task.findById(id).map(existingTask -> {
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setDueDate(updatedTask.getDueDate());
            existingTask.setCompleted(updatedTask.isCompleted());

            if (updatedTask.isCompleted()) {
                existingTask.setStatus(TaskStatus.COMPLETED);
            } else {
                java.time.LocalDate today = java.time.LocalDate.now();
                if (updatedTask.getDueDate() != null && updatedTask.getDueDate().toLocalDate().isBefore(today)) {
                    existingTask.setStatus(TaskStatus.OVERDUE);
                } else {
                    existingTask.setStatus(TaskStatus.IN_PROGRESS);
                }
            }

            return task.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }


    public void toBeDeleted(Long id){
        
         task.deleteById(id);
        
    }
  
    
}
