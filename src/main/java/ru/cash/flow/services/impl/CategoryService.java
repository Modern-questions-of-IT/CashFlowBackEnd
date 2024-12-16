package ru.cash.flow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.Category;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.enums.ETransaction;
import ru.cash.flow.mappers.CategoryMapper;
import ru.cash.flow.repositories.CategoryRepository;

import java.util.Date;
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

        category = setCreateTime(category);
        return categoryRepository.save(setCreateTime(category));
    }

    public Category getById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public List<Category> getAllByUser(Integer userId, ETransaction type) {
        return categoryRepository.getAllByUserAndType(userId, type);
    }

    private Category setCreateTime(Category category) {
        Date currentDate = new Date();
        category.setCreatedAt(currentDate);
        return category;
    }
}
