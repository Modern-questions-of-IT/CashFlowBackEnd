package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cash.flow.entities.RegularTransaction;

import java.util.List;

public interface RegularTransactionRepository extends JpaRepository<RegularTransaction, Integer> {
    public RegularTransaction findRegularTransactionByUser(Integer userId);
    List<RegularTransaction> findAllByUser(Integer userId);
}
