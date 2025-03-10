package ru.cash.flow.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.Category;
import ru.cash.flow.entities.User;
import ru.cash.flow.services.impl.UserServiceTemp;

@Component
public class CategoryMapper {

    @Autowired
    UserServiceTemp userServiceTemp;

    public Category toModel(CategoryDto dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
//        User user = userServiceTemp.get(dto.getUserId());
        category.setUser(dto.getUserId());
        category.setName(dto.getName());
        category.setType(dto.getType());

        return category;
    }
}
