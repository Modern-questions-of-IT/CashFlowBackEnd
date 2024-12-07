package ru.cash.flow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
    @Value("${bot.address}")
    private String botAddress;
    @Value("${bot.endpoint}")
    private String endpoint;
    public RegularTransaction createNew(RegularTransactionDto transactionDto) {
        RegularTransaction transaction = mapper.toModel(transactionDto);

        transaction.setNextOccurrence(calculateNextOccurence(transactionDto.getDate(),
                transactionDto.getDay(),
                transactionDto.getMonth(),
                transactionDto.getYear()));
        return regularTransactionRepository.save(transaction);
    }

//    public List<RegularTransactionDto> getAllDto() {
//        List<RegularTransaction> transactions = regularTransactionRepository.findAll();
//        List<RegularTransactionDto> toBotRegularTransactionDtos = new ArrayList<>();
//        for (RegularTransaction transaction : transactions) {
//            toBotRegularTransactionDtos.add(mapper.toBotDto(transaction));
//        }
//
//        return toBotRegularTransactionDtos;
//    }

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

    public List<RegularTransaction> getAllByUser(Integer id) {
        return regularTransactionRepository.findAllByUser(id);
    }

    public List<RegularTransaction> getAll() {
        return regularTransactionRepository.findAll();
    }

    private ToBotRegularTransactionDto toDto(RegularTransaction regularTransaction) {
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

    @Scheduled(fixedDelay = 3_600_000)
    private void sendRequestToOtherService(){
        List<RegularTransaction> transactions = getAll();

        Date today = new Date();
        for (RegularTransaction transaction : transactions) {
            if (transaction.getNextOccurrence().before(today)) {

                ToBotRegularTransactionDto dto = toDto(transaction);
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
