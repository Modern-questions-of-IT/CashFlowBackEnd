package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cash.flow.dto.StatisticInfo;
import ru.cash.flow.enums.ETimeInterval;
import ru.cash.flow.enums.TransactionType;
import ru.cash.flow.services.StatisticService;

import java.time.Instant;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/transaction")
    public ResponseEntity<StatisticInfo> getTransactionStatistic(
            @RequestParam Long userId,
            @RequestParam ETimeInterval interval,
            @RequestParam Instant dateTime,
            @RequestParam TransactionType type) {

        StatisticInfo statisticInfo = statisticService.getTransactionStatisticByTimeInterval(userId, interval, dateTime, type);
        return new ResponseEntity<>(statisticInfo, HttpStatus.OK);
    }

    @GetMapping("/transaction/custom")
    public ResponseEntity<StatisticInfo> getTransactionStatisticByCustomInterval(
            @RequestParam Long userId,
            @RequestParam Instant startDate,
            @RequestParam Instant endDate,
            @RequestParam TransactionType type) {

        StatisticInfo statisticInfo = statisticService.getTransactionStatisticByCustomTimeInterval(userId, startDate, endDate, type);
        return new ResponseEntity<>(statisticInfo, HttpStatus.OK);
    }
}
