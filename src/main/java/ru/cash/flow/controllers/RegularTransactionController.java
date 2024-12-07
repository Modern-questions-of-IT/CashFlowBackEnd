package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.cash.flow.dto.RegularTransactionDto;
import ru.cash.flow.dto.ToBotRegularTransactionDto;
import ru.cash.flow.entities.RegularTransaction;
import ru.cash.flow.services.RegularTransactionService;

import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/regular_transaction")
@EnableScheduling

public class RegularTransactionController {
    @Autowired
    RegularTransactionService regularTransactionService;


    @PostMapping("/register_new")
    public RegularTransaction create(@RequestBody RegularTransactionDto dto) {
        return regularTransactionService.createNew(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        regularTransactionService.delete(id);
    }

    @PutMapping("/update")
    public RegularTransaction update(@RequestBody RegularTransactionDto dto) {
        return regularTransactionService.update(dto);
    }

    @GetMapping("/getAll/{userId}")
    public List<RegularTransactionDto> getAll(@PathVariable Integer userId) {
        return regularTransactionService.getAllByUser(userId);
    }


}
