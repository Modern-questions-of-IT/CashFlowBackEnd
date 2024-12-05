package ru.cash.flow.dto;

import jakarta.annotation.Nonnull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long userId;
    private String type;
    private Integer categoryId;
    private String categoryName;
    private String title;
    private BigDecimal amount;
    private Date date;
    private Boolean isRecurring;
    private Long frequency;
    private Date nextOccurrence;
    private Date createdAt;
    private Date updatedAt;

}
