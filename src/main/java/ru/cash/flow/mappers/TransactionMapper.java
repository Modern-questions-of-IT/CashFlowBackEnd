package ru.cash.flow.mappers;

import org.mapstruct.Mapper;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toModel(TransactionDto dto);
}
