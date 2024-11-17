package ru.cash.flow.mappers;

import org.mapstruct.Mapper;
import ru.cash.flow.dto.UserDto;
import ru.cash.flow.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDto userDto);
}
