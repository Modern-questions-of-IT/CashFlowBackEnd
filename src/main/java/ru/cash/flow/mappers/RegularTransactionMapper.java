package ru.cash.flow.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cash.flow.dto.RegularTransactionDto;
import ru.cash.flow.dto.ToBotRegularTransactionDto;
import ru.cash.flow.entities.RegularTransaction;
import ru.cash.flow.enums.TransactionType;
import ru.cash.flow.services.impl.CategoryService;
import ru.cash.flow.services.UserService;

@Component
public class RegularTransactionMapper {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    public RegularTransaction toModel(RegularTransactionDto dto) {
        if (dto == null) {
            return null;
        }

        RegularTransaction transaction = new RegularTransaction();

        transaction.setUser(dto.getUserId());

        transaction.setType(dto.getType());
        transaction.setCategory(dto.getCategoryId());
        transaction.setTitle(dto.getTitle());
        transaction.setAmount(dto.getAmount());
        transaction.setCreatedAt(dto.getCreatedAt());
        transaction.setUpdatedAt(dto.getUpdatedAt());

        return transaction;
    }

    public ToBotRegularTransactionDto toBotDto(RegularTransaction entity) {
        if (entity == null) {
            return null;
        }

        ToBotRegularTransactionDto transaction = new ToBotRegularTransactionDto();

        transaction.setUserId(entity.getUser().longValue());
        transaction.setType(entity.getType());

        transaction.setCategoryName(categoryService.getById(entity.getCategory()).getName());
        transaction.setTitle(entity.getTitle());
        transaction.setAmount(entity.getAmount());
        transaction.setDate(entity.getNextOccurrence());

        return transaction;
    }
}
