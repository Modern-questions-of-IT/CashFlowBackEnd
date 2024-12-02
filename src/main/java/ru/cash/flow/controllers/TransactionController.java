package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.dto.TransactionsForPeriodDto;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.services.impl.TransactionService;

import java.util.List;

@Controller
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/register_new")
    public Transaction create(@RequestBody TransactionDto dto) {
        return transactionService.createNew(dto);
    }


//    @PostMapping("/change_title")
//    public void updateTitle() {
//
//    }
//
//    @PostMapping("/update")
//    public void update(@RequestBody Transaction transaction) {
//
//    }

    @GetMapping("/get/{id}")
    public Transaction get(@PathVariable Integer id) {
        return null;
    }

    @GetMapping("/getByTime")
    public List<Transaction> getByTime(@RequestBody TransactionsForPeriodDto dto) {
        return transactionService.getByUserIdAndTime(dto);
    }


}
