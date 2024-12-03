package ru.cash.flow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.cash.flow.dto.RegularTransactionDto;
import ru.cash.flow.dto.ToBotRegularTransactionDto;
import ru.cash.flow.entities.RegularTransaction;
import ru.cash.flow.services.RegularTransactionService;

import java.util.Date;
import java.util.List;

@Controller
@RestController
@RequestMapping("/regular_transaction")
@EnableScheduling

public class RegularTransactionController {
    @Autowired
    RegularTransactionService regularTransactionService;
    @Value("${bot.address}")
    private String botAddress;
    @Value("${bot.endpoint}")
    private String endpoint;

    @PostMapping("/register_new")
    public RegularTransaction create(@RequestBody RegularTransactionDto dto) {
        return regularTransactionService.createNew(dto);
    }

    @Scheduled(fixedDelay = 3_600_000)
    private void sendRequestToOtherService() {
        List<RegularTransaction> transactions = regularTransactionService.getAll();

        Date today = new Date();
        for (RegularTransaction transaction : transactions) {
            if (transaction.getNextOccurrence().before(today)) {

                ToBotRegularTransactionDto dto = regularTransactionService.toDto(transaction);
                StringBuilder requestURL = new StringBuilder();
                requestURL.append("http://")
                        .append(botAddress)
                        .append(endpoint);

                final RestTemplate restTemplate = new RestTemplate();
                final ToBotRegularTransactionDto stringPosts = restTemplate.postForObject(
                        requestURL.toString(),
                        dto,
                        ToBotRegularTransactionDto.class);
            }
        }
    }
}
