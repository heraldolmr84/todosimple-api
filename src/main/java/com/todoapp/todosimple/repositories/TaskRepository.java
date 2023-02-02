package com.todoapp.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {    

    List<Task> findByUser_id(Integer id);
}
