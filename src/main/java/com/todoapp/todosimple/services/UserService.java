package com.todoapp.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todoapp.todosimple.models.User;
import com.todoapp.todosimple.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    //find User by ID
    public User findById(Integer id) {

        Optional<User> user = this.userRepository.findById(id);
        
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado: " + id + " Tipo: " + User.class.getName()
        ));
    }

    /**
     * @param obj
     */
    @Transactional
    public User createUser(User obj) {

        obj.setId(null);
        obj = this.userRepository.save(obj);

        return obj;
    }

    //Update password of User
    @Transactional
    public User updatePass(User obj) {

        User newObj = findById(obj.getId());

        newObj.setPassword(obj.getPassword());

        return this.userRepository.save(newObj);
    }

    //delete user
    /**
     * @param id
     */
    public void deleteUser(Integer id) {

        User newObj = findById(id);

        try {
            this.userRepository.delete(newObj);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("Usuário não pode ser removido!");
        }
    }
    
}
