package ru.cash.flow.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.entities.TransactionCategory;
import ru.cash.flow.entities.User;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class TransactionDto {
    private User userId;
    private String type;
    private TransactionCategory categoryId;
    private String title;
    private BigDecimal amount;
    private Date date;
    private Boolean isRecurring;
    private Long frequency;
    private Date nextOccurrence;
    private Date createdAt;
    private Date updatedAt;

}
