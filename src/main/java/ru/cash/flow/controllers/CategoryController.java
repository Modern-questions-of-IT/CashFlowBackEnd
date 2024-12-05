package ru.cash.flow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.Category;
import ru.cash.flow.enums.ETransaction;
import ru.cash.flow.services.impl.CategoryService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/create")
    public Category create(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/get/{id}")
    public Category get(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @GetMapping("/get/{userId}/{type}")
    public List<Category> getAllByUser(@PathVariable Integer userId, @PathVariable ETransaction type) {
        return categoryService.getAllByUser(userId, type);
    }
}
