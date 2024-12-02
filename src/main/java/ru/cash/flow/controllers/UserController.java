package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cash.flow.dto.UserDto;
import ru.cash.flow.entities.User;
import ru.cash.flow.services.impl.UserServiceTemp;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceTemp userServiceTemp;

    //    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PostMapping("/create")
    public User create(@RequestBody UserDto user) {
        return userServiceTemp.create(user);
    }
}
