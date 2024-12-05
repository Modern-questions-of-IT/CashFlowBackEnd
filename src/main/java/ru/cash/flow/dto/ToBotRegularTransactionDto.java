package ru.cash.flow.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ToBotRegularTransactionDto {
    private Integer userId;
    private String type;
    private String categoryName;
    private String title;
    private BigDecimal amount;
    private Date date;
}
