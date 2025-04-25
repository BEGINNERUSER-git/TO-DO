package com.example.todo_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todo_backend.model.TaskModel;
import com.example.todo_backend.model.TaskModel.TaskStatus;
//import com.example.todo_backend.model.UserModel;

import java.util.List;


@Repository
public interface Taskrepo extends JpaRepository<TaskModel, Long> {

    // Find tasks by status
    @Query("SELECT t FROM TaskModel t WHERE t.status = :status")
    List<TaskModel> findByStatus(@Param("status") TaskStatus taskStatus);
    

//    List<TaskModel> findByUser(UserModel user);

    // // Find tasks created by the user (owner)
    // List<TaskModel> findByOwner(UserModel owner);

    // // Find tasks assigned to the user (assignee)
    // List<TaskModel> findByAssignee(UserModel assignee);


}


