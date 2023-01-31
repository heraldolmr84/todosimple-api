package com.todoapp.todosimple.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todoapp.todosimple.models.User;
import com.todoapp.todosimple.models.User.CreateUser;
import com.todoapp.todosimple.models.User.UpdateUser;
import com.todoapp.todosimple.services.UserService;

@RestController
@RequestMapping("/user/")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;

    //Search user from web interface!
    //localhost:8080/user/id
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

        User obj = this.userService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    //Create user in DB
    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj) {

        this.userService.createUser(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    //update user
    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User obj, @PathVariable Integer id) {
        
        obj.setId(id);
        this.userService.updatePass(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
