package com.picpaysimplificado.controllers;

import com.picpaysimplificado.doman.User.User;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    // ter uma instacia da classe user service
    @Autowired
    private UserService userService;

    @PostMapping // endpoint para a criação de um usuario
    // vai retornar um response entidy com o user no body
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){ // RequestBody para indicar qual vai ser o parametro da classe
        User newUser = userService.createUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    // vai retornar uma lista de usuario
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
