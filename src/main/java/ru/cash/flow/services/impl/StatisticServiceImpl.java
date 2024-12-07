package ru.cash.flow.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.StatisticInfo;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.enums.ETimeInterval;
import ru.cash.flow.enums.TransactionType;
import ru.cash.flow.mappers.TransactionMapper;
import ru.cash.flow.repositories.TransactionRepository;
import ru.cash.flow.services.StatisticService;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final ChartGenerator chartGenerator;

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public StatisticInfo getTransactionStatisticByTimeIntervalAndType(Long userId, ETimeInterval interval, Instant dateTime, TransactionType type) {
        List<TransactionType> types = new ArrayList<>();
        if (type.equals(TransactionType.ALL)) {
            types.add(TransactionType.INCOME);
            types.add(TransactionType.EXPENSE);
        } else {
            types.add(type);
        }
        List<TransactionDto> transactions = switch (interval) {
            case TODAY -> getTransactionsByUserAndDay(userId, dateTime, types).stream()
                    .map(transactionMapper::toDto)
                    .toList();
            case WEEK -> getTransactionsByUserAndWeek(userId, dateTime, types).stream()
                    .map(transactionMapper::toDto)
                    .toList();
            case MONTH -> getTransactionsByUserAndDate(userId, dateTime, types).stream()
                    .map(transactionMapper::toDto)
                    .toList();
            case YEAR -> getTransactionsByUserAndYear(userId, dateTime, types).stream()
                    .map(transactionMapper::toDto)
                    .toList();
            case EXIT -> null;
        };

        Map<String, Double> categoryDistribution = calculateCategoryDistribution(transactions);

        return StatisticInfo.builder()
                .diagramPng(chartGenerator.generatePieChart(categoryDistribution))
                .transactions(transactions)
                .build();
    }



    @Override
    public StatisticInfo getTransactionStatisticByCustomTimeInterval(Long userId, Instant startDate, Instant endDate, TransactionType type) {
        List<TransactionType> types = new ArrayList<>();
        if (type.equals(TransactionType.ALL)) {
            types.add(TransactionType.INCOME);
            types.add(TransactionType.EXPENSE);
        } else {
            types.add(type);
        }
        List<TransactionDto> transactions = getTransactionsByUserAndDay(userId, startDate, endDate, types).stream()
                .map(transactionMapper::toDto)
                .toList();
        ;

        Map<String, Double> categoryDistribution = calculateCategoryDistribution(transactions);

        return StatisticInfo.builder()
                .diagramPng(chartGenerator.generatePieChart(categoryDistribution))
                .transactions(transactions)
                .build();
    }


    private Map<String, Double> calculateCategoryDistribution(List<TransactionDto> transactions) {
        if (transactions.isEmpty()) {
            return Map.of();
        }

        Map<String, BigDecimal> categorySums = transactions.stream()
                .collect(Collectors.groupingBy(TransactionDto::getCategoryName,
                        Collectors.mapping(TransactionDto::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        BigDecimal totalSum = categorySums.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return categorySums.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> totalSum.compareTo(BigDecimal.ZERO) == 0 ? 0.0 : (entry.getValue().multiply(BigDecimal.valueOf(100.0)).divide(totalSum, BigDecimal.ROUND_HALF_UP)).doubleValue()
                ));
    }

    private List<Transaction> getTransactionsByUserAndDay(Long userId, Instant startDate, Instant endDate, List<TransactionType> type) {
        LocalDate startlocalDate = startDate.atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
        LocalDate endlocalDate = endDate.atZone(ZoneId.of("Europe/Moscow")).toLocalDate();

        LocalDateTime dayOfMonthTime = startlocalDate.atStartOfDay();
        ZonedDateTime dayZonedDateTime = dayOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime first = dayZonedDateTime.toLocalDateTime();

        LocalDateTime dayEndOfMonthTime = endlocalDate.atTime(23, 59, 59, 999_999_999);
        ZonedDateTime dayEndZonedDateTime = dayEndOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime second = dayEndZonedDateTime.toLocalDateTime();

        return transactionRepository.findByUserIdAndCreatedAtBetween(userId, first, second, type);
    }

    private List<Transaction> getTransactionsByUserAndDay(Long userId, Instant date, List<TransactionType> type) {
        LocalDate localDate = date.atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();

        LocalDate dayLocalDate = LocalDate.of(year, month, day);
        LocalDateTime dayOfMonthTime = dayLocalDate.atStartOfDay();
        ZonedDateTime dayZonedDateTime = dayOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime first = dayZonedDateTime.toLocalDateTime();

        LocalDate dayEndLocalDate = LocalDate.of(year, month, day);
        LocalDateTime dayEndOfMonthTime = dayEndLocalDate.atTime(23, 59, 59, 999_999_999);
        ZonedDateTime dayEndZonedDateTime = dayEndOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime second = dayEndZonedDateTime.toLocalDateTime();

        return transactionRepository.findByUserIdAndCreatedAtBetween(userId, first, second, type);
    }

    private List<Transaction> getTransactionsByUserAndWeek(Long userId, Instant date, List<TransactionType> type) {
        LocalDate localDate = date.atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();

        LocalDate dayLocalDate = LocalDate.of(year, month, day);
        LocalDateTime dayOfMonthTime = dayLocalDate.atStartOfDay().minusDays(6);
        ZonedDateTime dayZonedDateTime = dayOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime first = dayZonedDateTime.toLocalDateTime();

        LocalDate dayEndLocalDate = LocalDate.of(year, month, day);
        LocalDateTime dayEndOfMonthTime = dayEndLocalDate.atTime(23, 59, 59, 999_999_999);

        ZonedDateTime dayEndZonedDateTime = dayEndOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime second = dayEndZonedDateTime.toLocalDateTime();

        return transactionRepository.findByUserIdAndCreatedAtBetween(userId, first, second, type);
    }

    private List<Transaction> getTransactionsByUserAndDate(Long userId, Instant date, List<TransactionType> type) {
        LocalDate localDate = date.atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDateTime firstDayOfMonthTime = firstDayOfMonth.atStartOfDay();
        ZonedDateTime firstZonedDateTime = firstDayOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime first = firstZonedDateTime.toLocalDateTime();

        LocalDate lastDayOfMonth = LocalDate.of(year, month, firstDayOfMonth.lengthOfMonth());
        LocalDateTime lastDayOfMonthTime = lastDayOfMonth.atTime(23, 59, 59, 999_999_999);
        ZonedDateTime lastZonedDateTime = lastDayOfMonthTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime last = lastZonedDateTime.toLocalDateTime();

        return transactionRepository.findByUserIdAndMonth(userId, first, last, type);
    }

    private List<Transaction> getTransactionsByUserAndYear(Long userId, Instant date, List<TransactionType> type) {

        LocalDate localDate = date.atZone(ZoneId.of("Europe/Moscow")).toLocalDate();
        int year = localDate.getYear();
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        LocalDateTime firstDayOfYearTime = firstDayOfYear.atStartOfDay();
        ZonedDateTime firstZonedDateTime = firstDayOfYearTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime first = firstZonedDateTime.toLocalDateTime();

        LocalDate lastDayOfYear = LocalDate.of(year, 12, 31);
        LocalDateTime lastDayOfYearTime = lastDayOfYear.atTime(23, 59, 59, 999_999_999);
        ZonedDateTime lastZonedDateTime = lastDayOfYearTime.atZone(ZoneId.of("Europe/Moscow"));
        LocalDateTime last = lastZonedDateTime.toLocalDateTime();

        return transactionRepository.findByUserIdAndYear(userId, first, last, type);
    }
}
