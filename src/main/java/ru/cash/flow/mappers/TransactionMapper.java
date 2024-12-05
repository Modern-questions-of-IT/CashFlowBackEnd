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

        transaction.setUser(userServiceTemp.get(dto.getUserId()));

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

    public TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDto dto = new TransactionDto();
        // Заполнение полей dto из transaction
        dto.setUserId(transaction.getUser() != null ? transaction.getUser().getId().longValue() : null);
        dto.setType(transaction.getType());
        dto.setCategoryId(transaction.getCategory() != null ? transaction.getCategory().getId() : null);
        dto.setTitle(transaction.getTitle());
        dto.setAmount(transaction.getAmount());
        dto.setDate(transaction.getDate());
        dto.setIsRecurring(transaction.getIsRecurring());
        dto.setFrequency(transaction.getFrequency());
        dto.setNextOccurrence(transaction.getNextOccurrence());
        dto.setCreatedAt(transaction.getCreatedAt());
        dto.setUpdatedAt(transaction.getUpdatedAt());

        return dto;
    }
}
