package ru.cash.flow.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.Category;
import ru.cash.flow.entities.User;
import ru.cash.flow.services.UserService;

import java.util.Optional;

@Component
public class CategoryMapper {

    @Autowired
    UserService userService;
    public Category toModel(CategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();
        User user = userService.get(dto.getUserId());
        category.setUser(user);
        category.setName(dto.getName());

        return category;
    }
}
