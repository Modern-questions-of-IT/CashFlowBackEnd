package ru.cash.flow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.Category;
import ru.cash.flow.services.impl.CategoryService;

@Controller
@RestController
@RequestMapping("/category/")
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
}
