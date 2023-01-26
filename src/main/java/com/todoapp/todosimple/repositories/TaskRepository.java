package com.todoapp.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {    
}
