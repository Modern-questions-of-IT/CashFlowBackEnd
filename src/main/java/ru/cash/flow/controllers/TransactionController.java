package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.TransactionService;
import ru.cash.flow.entities.Transaction;

@Controller
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/register_new")
    public Transaction create() {

    }


    @PostMapping("/change_title")
    public void updateTitle() {

    }

    @PostMapping("/update")
    public void update(@RequestBody Transaction transaction) {

    }



}
