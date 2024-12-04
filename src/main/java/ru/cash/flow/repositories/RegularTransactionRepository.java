package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cash.flow.entities.RegularTransaction;

public interface RegularTransactionRepository extends JpaRepository<RegularTransaction, Integer> {
    public RegularTransaction findRegularTransactionByUserId(Integer userId);
}
