package ru.cash.flow.mappers;

import org.mapstruct.Mapper;
import ru.cash.flow.dto.CategoryDto;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.TransactionCategory;

@Mapper(componentModel = "spring")
    public interface CategoryMapper {
    TransactionCategory toModel(CategoryDto dto);
}
