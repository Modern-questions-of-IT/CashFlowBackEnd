package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


}
