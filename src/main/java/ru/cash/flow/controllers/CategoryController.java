package ru.cash.flow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.entities.TransactionCategory;
import ru.cash.flow.services.CategoryService;

@Controller
@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/create")
    public TransactionCategory create(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/get/{id}")
    public TransactionCategory get(@PathVariable Long id) {
        return null;

    }

}
