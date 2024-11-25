package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.dto.TransactionsForPeriodDto;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.mappers.TransactionMapper;
import ru.cash.flow.repositories.TransactionRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionMapper mapper;

    public Transaction createNew(TransactionDto transactionDto) {
        Transaction transaction = mapper.toModel(transactionDto);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getByUserIdAndTime(TransactionsForPeriodDto dto) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom;
        Date dateTo;
        try {
            dateFrom = format.parse(dto.getFromDate());
            dateTo = format.parse(dto.getToDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Transaction> transactions = transactionRepository.findByUserIdAndCreatedAtBetween(dto.getUserId(), dateFrom, dateTo);
        System.out.println("doc");
        return transactions;
    }
}
