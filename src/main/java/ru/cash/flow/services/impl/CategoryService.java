package ru.cash.flow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.Category;
import ru.cash.flow.mappers.CategoryMapper;
import ru.cash.flow.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category create(CategoryDto categoryDto) {
        Category category = categoryMapper.toModel(categoryDto);

        return categoryRepository.save(category);
    }

    public Category getById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }
}
