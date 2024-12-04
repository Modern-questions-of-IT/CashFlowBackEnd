package ru.cash.flow.mappers;

import org.springframework.stereotype.Component;
import ru.cash.flow.dto.UserDto;
import ru.cash.flow.entities.User;

@Component
public class UserMapper {
    public User toModel(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());

        return user;
    }
}
