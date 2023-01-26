package com.todoapp.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.todosimple.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {    
}
