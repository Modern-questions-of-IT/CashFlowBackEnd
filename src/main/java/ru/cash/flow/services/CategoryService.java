package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.entities.TransactionCategory;
import ru.cash.flow.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<TransactionCategory> getAll() {
        return categoryRepository.findAll();
    }
}
