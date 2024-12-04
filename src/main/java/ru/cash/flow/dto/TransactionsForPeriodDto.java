package ru.cash.flow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsForPeriodDto {
    private String fromDate;
    private String toDate;
    private Integer userId;
}
