package ru.cash.flow.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cash.flow.entities.TransactionCategory;
import ru.cash.flow.services.CategoryService;

@Controller
@RestController
@RequestMapping("/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping("/create")
    public TransactionCategory create() {
        return null;
    }

    @GetMapping("/get/{id}")
    public TransactionCategory get() {
        return null;

    }

}
