package ru.cash.flow.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TransactionsForPeriodDto {
    private String fromDate;
    private String toDate;
    private Integer userId;
}
