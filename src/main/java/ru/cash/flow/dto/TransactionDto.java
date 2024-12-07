package ru.cash.flow.dto;

import jakarta.annotation.Nonnull;
import lombok.*;
import ru.cash.flow.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer userId;
    private TransactionType type;
    private Integer categoryId;
    private String categoryName;
    private String title;
    private BigDecimal amount;
    private Date date;
    private Date nextOccurrence;
    private Date createdAt;

}
