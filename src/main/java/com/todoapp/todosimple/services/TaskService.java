package com.todoapp.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todoapp.todosimple.models.Task;
import com.todoapp.todosimple.models.User;
import com.todoapp.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;


    //Find by ID
    public Task findById(Integer id) {

        Optional<Task> task = this.taskRepository.findById(id);
        
        return task.orElseThrow( () -> new RuntimeException(
            "Tarefa não encontrada! id: " + id + ", tipo: " +Task.class.getName()
        ));
    }

    public List<Task> findAllByUserId(Integer userId) {

        List<Task> tasks = this.taskRepository.findByUser_id(userId);

        return tasks;
    }

    @Transactional
    public Task createTask(Task obj) {

        User user = this.userService.findById(obj.getUser().getId());

        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);

        return obj;
    }

    @Transactional
    public Task updateTask(Task obj) {

        Task newObj  = findById(obj.getId());

        newObj.setDescription(obj.getDescription());

        return this.taskRepository.save(obj);
    }

    //delete task
    public void delete(Integer id) {

        Task newObj = findById(id);

        try {
            this.taskRepository.delete(newObj);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Tarefa não pode ser removida!");
        }
    }
}
