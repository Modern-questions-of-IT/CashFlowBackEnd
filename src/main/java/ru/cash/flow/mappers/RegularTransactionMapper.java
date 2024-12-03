package ru.cash.flow.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cash.flow.dto.RegularTransactionDto;
import ru.cash.flow.dto.ToBotRegularTransactionDto;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.RegularTransaction;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.services.CategoryService;
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

        transaction.setUser(userService.get(dto.getUserId()));

        transaction.setType(dto.getType());
        transaction.setCategory(categoryService.getById(dto.getCategoryId()));
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

        transaction.setUserId(entity.getUser().getId());
        transaction.setType(entity.getType());
        transaction.setCategoryId(
                entity.getCategory().getId());
        transaction.setTitle(entity.getTitle());
        transaction.setAmount(entity.getAmount());
        transaction.setCreatedAt(entity.getCreatedAt());
        transaction.setUpdatedAt(entity.getUpdatedAt());

        return transaction;
    }
}
