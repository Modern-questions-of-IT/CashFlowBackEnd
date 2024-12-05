package ru.cash.flow.dto;

import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.enums.ETransaction;

@Setter
@Getter
public class CategoryDto {
    private String name;
    private Integer userId;
    private ETransaction type;

}
