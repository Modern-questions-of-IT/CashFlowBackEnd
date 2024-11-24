package ru.cash.flow.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.services.CategoryService;
import ru.cash.flow.services.UserService;

@Component
public class TransactionMapper {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public Transaction toModel(TransactionDto dto) {
        if (dto == null) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setUser(userService.get(dto.getUserId()));

        transaction.setType(dto.getType());
        transaction.setCategory(categoryService.getById(dto.getCategoryId()));
        transaction.setTitle(dto.getTitle());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setIsRecurring(dto.getIsRecurring());
        transaction.setFrequency(dto.getFrequency());
        transaction.setNextOccurrence(dto.getNextOccurrence());
        transaction.setCreatedAt(dto.getCreatedAt());
        transaction.setUpdatedAt(dto.getUpdatedAt());

        return transaction;
    }
}
