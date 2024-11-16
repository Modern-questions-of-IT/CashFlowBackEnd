package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cash.flow.entities.User;
import ru.cash.flow.services.UserService;

@Controller
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("crete")
    public User create(@RequestBody User user) {
        return null;
    }
}
