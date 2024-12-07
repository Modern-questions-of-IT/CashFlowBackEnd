package ru.cash.flow.services;


import org.springframework.stereotype.Service;
import ru.cash.flow.dto.StatisticInfo;
import ru.cash.flow.enums.ETimeInterval;
import ru.cash.flow.enums.TransactionType;

import java.time.Instant;

@Service
public interface StatisticService {
    StatisticInfo getTransactionStatisticByTimeIntervalAndType(Long userId, ETimeInterval interval, Instant dateTime, TransactionType type);

    StatisticInfo getTransactionStatisticByCustomTimeInterval(Long userId, Instant startDate, Instant endDate, TransactionType type);

}
