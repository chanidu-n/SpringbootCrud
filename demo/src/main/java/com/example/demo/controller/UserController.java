package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping (value = "api/v1/")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping ("/getUsers")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping ("/adduser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        return userService.saveUser(userDTO);

    }

}


