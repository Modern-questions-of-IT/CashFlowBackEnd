package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cash.flow.dto.RegularTransactionDto;
import ru.cash.flow.dto.ToBotRegularTransactionDto;
import ru.cash.flow.entities.RegularTransaction;
import ru.cash.flow.enums.TransactionType;
import ru.cash.flow.mappers.RegularTransactionMapper;
import ru.cash.flow.repositories.RegularTransactionRepository;

import java.util.*;

@Service
public class RegularTransactionService {
    @Autowired
    RegularTransactionRepository regularTransactionRepository;
    @Autowired
    RegularTransactionMapper mapper;
    public RegularTransaction createNew(RegularTransactionDto transactionDto) {
        RegularTransaction transaction = mapper.toModel(transactionDto);

        transaction.setNextOccurrence(calculateNextOccurence(transactionDto.getDate(),
                transactionDto.getDay(),
                transactionDto.getMonth(),
                transactionDto.getYear()));
        return regularTransactionRepository.save(transaction);
    }

    public List<ToBotRegularTransactionDto> getAllDto() {
        List<RegularTransaction> transactions = regularTransactionRepository.findAll();
        List<ToBotRegularTransactionDto> toBotRegularTransactionDtos = new ArrayList<>();
        for (RegularTransaction transaction : transactions) {
            toBotRegularTransactionDtos.add(mapper.toBotDto(transaction));
        }

        return toBotRegularTransactionDtos;
    }

    public List<RegularTransaction> getAll() {
        List<RegularTransaction> transactions = regularTransactionRepository.findAll();

        return transactions;
    }

    public void delete(Integer id) {
        Optional<RegularTransaction> toDelete = regularTransactionRepository.findById(id);
        toDelete.ifPresent(regularTransaction -> regularTransactionRepository.delete(regularTransaction));
    }

    public RegularTransaction update(RegularTransactionDto dto) {
        RegularTransaction found = regularTransactionRepository.findRegularTransactionByUser(dto.getUserId());
        found.setType(TransactionType.valueOf(dto.getType()));
        found.setTitle(dto.getTitle());
        found.setAmount(dto.getAmount());
        Date nextOccurance = calculateNextOccurence(new Date(), dto.getDay(), dto.getMonth(),dto.getYear());
        found.setNextOccurrence(nextOccurance);

        return regularTransactionRepository.save(found);
    }

    public ToBotRegularTransactionDto toDto(RegularTransaction regularTransaction) {
        return mapper.toBotDto(regularTransaction);
    }

    private Date calculateNextOccurence(Date fromDate, Integer days, Integer months, Integer years) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(fromDate); //устанавливаем дату, с которой будет производить операции
        instance.add(Calendar.DAY_OF_MONTH, days);
        instance.add(Calendar.MONTH, months);
        instance.add(Calendar.YEAR, years);
        Date newDate = instance.getTime(); // получаем измененную дату

        return newDate;
    }
}
