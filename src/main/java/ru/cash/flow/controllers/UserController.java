package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.dto.UserDto;
import ru.cash.flow.entities.User;
import ru.cash.flow.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostMapping("/create")
    public User create(@RequestBody UserDto user) {
        return userService.create(user);
    }
}
