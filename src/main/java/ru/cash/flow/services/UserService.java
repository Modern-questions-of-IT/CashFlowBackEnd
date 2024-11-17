package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.UserDto;
import ru.cash.flow.entities.User;
import ru.cash.flow.mappers.UserMapper;
import ru.cash.flow.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public User create(UserDto userDto) {
        User user = userMapper.toModel(userDto);
        return userRepository.save(user);
    }


}
