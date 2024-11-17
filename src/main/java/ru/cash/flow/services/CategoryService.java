package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.TransactionCategory;
import ru.cash.flow.mappers.CategoryMapper;
import ru.cash.flow.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

    public List<TransactionCategory> getAll() {
        return categoryRepository.findAll();
    }

    public TransactionCategory create(CategoryDto categoryDto) {
        TransactionCategory transactionCategory = categoryMapper.toModel(categoryDto);
        return categoryRepository.save(transactionCategory);
    }
}
