package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.TransactionDto;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.mappers.TransactionMapper;
import ru.cash.flow.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionMapper mapper;

    public Transaction createNew(TransactionDto transactionDto) {
        Transaction transaction = mapper.toModel(transactionDto);
        transactionRepository.save(transaction);
        return null;
    }
}
