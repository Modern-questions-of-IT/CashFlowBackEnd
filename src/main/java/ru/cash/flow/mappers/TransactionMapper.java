package ru.cash.flow.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.services.impl.CategoryService;
import ru.cash.flow.services.impl.UserServiceTemp;

@Component
public class TransactionMapper {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserServiceTemp userServiceTemp;

    public Transaction toModel(TransactionDto dto) {
        if (dto == null) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setUser(dto.getUserId());

        transaction.setType(dto.getType());
        transaction.setCategory(dto.getCategoryId());
        transaction.setTitle(dto.getTitle());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setDate(dto.getDate());
        transaction.setNextOccurrence(dto.getNextOccurrence());

        return transaction;
    }

    public TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDto dto = new TransactionDto();
        dto.setUserId(transaction.getUser() != null ? transaction.getUser() : null);
        // Заполнение полей dto из transaction
        dto.setUserId(transaction.getUser() != null ? transaction.getUser() : null);
        dto.setType(transaction.getType());
        dto.setCategoryId(transaction.getCategory() != null ? transaction.getCategory() : null);
        dto.setTitle(transaction.getTitle());
        dto.setAmount(transaction.getAmount());
        dto.setDate(transaction.getDate());
        dto.setNextOccurrence(transaction.getNextOccurrence());
        dto.setCreatedAt(transaction.getDate());

        return dto;
    }
}
