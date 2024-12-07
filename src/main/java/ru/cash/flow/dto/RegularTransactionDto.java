package ru.cash.flow.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.entities.Category;
import ru.cash.flow.entities.User;
import ru.cash.flow.enums.ETransaction;
import ru.cash.flow.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class RegularTransactionDto {
    private Integer userId;
    private TransactionType type;
    private Integer categoryId;
    private String categoryName;
    private String title;
    private BigDecimal amount;
    private Date date;
    private Integer day;
    private Integer month;
    private Integer year;
    private Date createdAt;
    private Date updatedAt;
}
